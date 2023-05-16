package com.squadsandshots_android.presentation.mainGameRoom

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.squadsandshots_android.firebaseModels.*
import com.squadsandshots_android.localStorage.BasicRoom
import com.squadsandshots_android.useCases.AddRoomListenerUseCase
import com.squadsandshots_android.useCases.GetStoredBasicRoomUseCase
import javax.inject.Inject

class MainGameRoomViewModel @Inject constructor(
    private val getStoredBasicRoomUseCase: GetStoredBasicRoomUseCase,
    private val addRoomListenerUseCase: AddRoomListenerUseCase,
): ViewModel() {

    lateinit var basicRoom: BasicRoom

    val players: MutableState<List<Player>> = mutableStateOf(mutableListOf())
    val generalRules: MutableState<List<GeneralRule>> = mutableStateOf(mutableListOf())
    val nominatedRules: MutableState<List<NominatedRule>> = mutableStateOf(mutableListOf())
    val secretTasks: MutableState<List<SecretTasks>> = mutableStateOf(mutableListOf())

    init { getRoomDetails() }

    private fun getRoomDetails() {
        getStoredBasicRoomUseCase.invoke(viewModelScope) { result ->
            result.result(
                onSuccess = {
                    it?.let {
                        basicRoom = it
                        createValueEventListener(it.roomCode)
                    }
                },
                onFailure = {

                }
            )
        }
    }

    private fun createValueEventListener(roomCode: String) {
        val listenerRequest = monitorRoomChangesListener(roomCode)
        addRoomListenerUseCase.invoke(viewModelScope, listenerRequest) { result ->
            result.result(
                onSuccess = {},
                onFailure = {}
            )
        }
    }

    private fun monitorRoomChangesListener(roomCode: String): ListenerRequest {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.getValue(RoomModel::class.java)?.let { roomModel ->
                        players.value = playerHashesToPlayers(roomModel.players)
                        roomModel.generalRules?.let {generalRules.value = it}
                        roomModel.nominatedRules?.let {nominatedRules.value = it}
                        roomModel.secretTasks?.let {secretTasks.value = it}
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        return ListenerRequest(listener, roomCode)
    }

    private fun playerHashesToPlayers(players: HashMap<String, Player>): List<Player> {
        val playersList = mutableListOf<Player>()
        players.forEach{ playersList.add(it.value) }
        return playersList
    }
}