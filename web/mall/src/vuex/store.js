import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  isLoading: {"isshow":false,"text":"加载中"},
  direction: 'forward',
  user: null,
  robot:null
}
export default new Vuex.Store({
  state,
  mutations: {
    UPDATE_LOADING (state, status) {
      state.isLoading = status
    },
    UPDATE_DIRECTION (state, direction) {
      state.direction = direction
    },
    UPDATE_USER (state, user) {
      state.user = user
    },
    UPDATE_ROBOT (state, robot) {
      state.robot = robot
    }
  }
})
