/*
 * 
 * @Author: ice-Yao
 * @Date:   2016-09-22 10:59:26
 * @Last Modified by:   ice-Yao
 * @Last Modified time: 2016-09-22 10:59:26
 * @parent component
 */
	


<template>
	<c-number :number="number"></number>
</template>

<script>
export default {
	data() {
			return {
				number: {
					value: 1
				}
			}
		},
		methods: {

		},
		components: {
			"c-number": require('../components/number.vue')

		}
}</script>