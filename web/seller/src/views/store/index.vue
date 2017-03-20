<template>
    <div>
        <transition :name="'vux-pop-' + ($store.state.direction === 'forward' ? 'in' : 'out')">
            <router-view class="router-view"></router-view>
        </transition>
    </div>

</template>
<script>
import utils from '../../libs/utils.js';
export default {
}
</script>