export default () => ({
  value: {
    type: String,
    default: ''
  },
  renderMonth: {
    type: Array, // [2018, 8]
    default () {
      return [null, null]
    }
  },
  startDate: {
    type: String
  },
  endDate: {
    type: String
  },
  showLastMonth: {
    type: Boolean,
    default: true
  },
  showNextMonth: {
    type: Boolean,
    default: true
  },
  highlightWeekend: {
    type: Boolean,
    default: false
  },
  returnSixRows: {
    type: Boolean,
    default: true
  },
  hideHeader: {
    type: Boolean,
    default: false
  },
  hideWeekList: {
    type: Boolean,
    default: false
  },
  replaceTextList: {
    type: Object,
    default () {
      return {}
    }
  },
  weeks: {
    type: Array,
    default () {
      return ['日', '一', '二', '三', '四', '五', '六']
    }
  },
  weeksList: {
    type: Array,
    default () {
      return ['日', '一', '二', '三', '四', '五', '六']
    }
  },
  renderFunction: {
    type: Function,
    default: () => ''
  },
  renderOnValueChange: {
    type: Boolean,
    default: true
  },
  disablePast: {
    type: Boolean,
    default: false
  },
  disableFuture: {
    type: Boolean,
    default: false
  }
})
