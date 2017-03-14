import ConfirmComponent from '../components/confirm.vue'

let $vm
let watcher

export default {
  install (vue) {
    if (!$vm) {
      const Confirm = vue.extend(ConfirmComponent)
      $vm = new Confirm({
        el: document.createElement('div')
      })
      document.body.appendChild($vm.$el)
    }

    const confirm = {
      show (options) {
        // destroy watcher
        watcher && watcher()
        if (typeof options === 'object') {
          for (let i in options) {
            if (i !== 'content') {
              $vm[i] = options[i]
            } else {
              $vm.$el.querySelector('.weui_dialog_bd').innerHTML = options['content']
            }
          }
        }
        if (options.onConfirm) {
          watcher = $vm.$watch('confirm', (val) => {
            val && options.onConfirm && options.onConfirm($vm)
            $vm.confirm = false;
          })
        }
        if (options.onCancel) {
          watcher = $vm.$watch('cancel', (val) => {
            val && options.onCancel && options.onCancel($vm)
            $vm.cancel = false;
          })
        }
        $vm.show = true
      },
      hide () {
        $vm.show = false
      }
    }

    // all Vux's plugins are included in this.$vux
    if (!vue.$vux) {
      vue.$vux = {
        confirm
      }
    } else {
      vue.$vux.confirm = confirm
    }

    vue.mixin({
      created: function () {
        this.$vux = vue.$vux
      }
    })
  }
}
