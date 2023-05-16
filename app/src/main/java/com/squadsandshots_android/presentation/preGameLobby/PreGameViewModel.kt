package com.squadsandshots_android.presentation.preGameLobby

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.squadsandshots_android.firebaseModels.ListenerRequest
import com.squadsandshots_android.firebaseModels.Player
import com.squadsandshots_android.firebaseModels.RoomModel
import com.squadsandshots_android.presentation.models.GameType
import com.squadsandshots_android.presentation.models.StartGameDetails
import com.squadsandshots_android.requestModels.JoinModel
import com.squadsandshots_android.useCases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

@HiltViewModel
class PreGameViewModel @Inject constructor(
    private val createRoomListenerUseCase: CreateRoomListenerUseCase,
    private val getOrCreateUniqueUserUseCase: GetOrCreateUniqueUserUseCase,
    private val createRoomUseCase: CreateRoomUseCase,
    private val joinRoomUseCase: JoinRoomUseCase,
    private val addRoomListenerUseCase: AddRoomListenerUseCase
) : ViewModel() {

    var listenerActive = false
    val players: MutableState<List<String>> = mutableStateOf(listOf())
    val roomCode: MutableState<String> = mutableStateOf("")

    private val _events: MutableStateFlow<MainPageViewEvent?> = MutableStateFlow(null)
    val events: StateFlow<MainPageViewEvent?> get() = _events.asStateFlow()

    fun init(startGameDetails: StartGameDetails) {
        // This isn't required, but just for safety
        if(listenerActive) return
        startGameDetails.createGameStart?.let {
            val code = generateGameCode()
            createRoom(code, it.name, it.gameType)
        }
        startGameDetails.joinGameStart?.let { joinRoom(it.gameCode, it.name) }
    }

    fun startGame() {

    }

    private fun createRoom(gameCode: String, name: String, gameType: GameType) {
        getOrCreateUniqueUserUseCase.invoke(viewModelScope, name) { result ->
            result.result(
                onSuccess = { player ->
                    val newRoom = RoomModel(roomCode = gameCode, gameType = gameType)
                    newRoom.players[player.id] = player
                    createRoomUseCase.invoke(viewModelScope, newRoom) { result ->
                        result.result(
                            onSuccess = { createValueEventListener(gameCode) },
                            onFailure = {}
                        )
                    }
                },
                onFailure = {}
            )
        }
    }

    private fun generateGameCode(): String {
        return UUID.randomUUID().toString().substring(0, 6)
    }

    private fun joinRoom(roomCode: String, playerName: String) {
        this.roomCode.value = roomCode
        getOrCreateUniqueUserUseCase.invoke(viewModelScope, playerName) { result ->
            result.result(
                onSuccess = {
                    val joinModel = JoinModel(roomCode, it)
                    joinRoomUseCase.invoke(viewModelScope, joinModel) { result ->
                        result.result(
                            onSuccess = {
                                createValueEventListener(roomCode)
                            },
                            onFailure = {}
                        )
                    }
                },
                onFailure = {}
            )
        }
    }

    private fun createValueEventListener(roomCode: String) {
        this.roomCode.value = roomCode
        val listenerRequest = monitorRoomChangesListener(roomCode)
        addRoomListenerUseCase.invoke(viewModelScope, listenerRequest) { result ->
            result.result(
                onSuccess = { listenerActive = true },
                onFailure = {}
            )
        }
    }

//    fun getGeneralRules() {
//        val rulesListeners = createRuleListeners()
//        getAllRulesUseCase.invoke(viewModelScope, rulesListeners) {}
//    }
//
//    private fun createRuleListeners(): RuleListeners {
//        val success = OnSuccessListener<QuerySnapshot> {
//            _generalRules.value = it.documents[0].toObject(Rules::class.java)
//        }
//        val failure = OnFailureListener {
//            Log.d("Creare rule listeners", "Failed, unsure what to do here just yet")
//        }
//        return RuleListeners(success, failure)
//
//    }

    private fun monitorRoomChangesListener(roomCode: String): ListenerRequest {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.getValue(RoomModel::class.java)?.players?.let {
                        players.value = playersToStrings(it)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        return ListenerRequest(listener, roomCode)
    }

    private fun playersToStrings(players: HashMap<String, Player>): List<String> {
        val playerList = mutableListOf<String>()
        players.forEach { playerList.add(it.value.name) }
        return playerList
    }

//    fun assignRules(rules: Rules, players: List<Player>): Rules {
//        val availablePlayers = players as MutableList
//        rules.generalRules.forEach {
//            it.players = players as ArrayList<Player>
//        }
//
//        rules.secretTasks.forEach {
//            val assignedPlayer = availablePlayers.random()
//            availablePlayers.remove(assignedPlayer)
//            it.players.add(assignedPlayer)
//        }
//        return rules
//    }

//    fun filterRules(rules: Rules): Rules {
//        val shuffledGeneralList = rules.generalRules.shuffled()
//        val shuffledNominatedList = rules.nominatedRules.shuffled()
//        val shuffledSecretList = rules.secretTasks.shuffled()
//
//        if (shuffledGeneralList.size < 7) {
//            rules.generalRules = shuffledGeneralList
//        } else {
//            rules.generalRules = shuffledGeneralList.subList(0, 6)
//        }
//
//        if (shuffledNominatedList.size < 3) {
//            rules.nominatedRules = shuffledNominatedList
//        } else {
//            rules.nominatedRules = shuffledNominatedList.subList(0, 2)
//        }
//
//        if (shuffledSecretList.size < 3) {
//            rules.secretTasks =
//                shuffledSecretList.subList(0, Random.nextInt(shuffledSecretList.size))
//        } else {
//            rules.secretTasks = shuffledSecretList.subList(0, Random.nextInt(3))
//        }
//        return rules
//    }

    fun convertIntoPlayers(players: HashMap<String, Player>): List<Player> {
        val playerList = mutableListOf<Player>()
        players.forEach {
            playerList.add(it.value)
        }
        return playerList
    }

    sealed class MainPageViewEvent {
        object CreateGameSuccess : MainPageViewEvent()
        object JoinGameSuccess : MainPageViewEvent()
        data class CreateGameFailed(val message: String) : MainPageViewEvent()
        data class JoinGameFailed(val message: String) : MainPageViewEvent()
    }

    companion object {
        private const val MAX_ATTEMPTS = 5
    }

}