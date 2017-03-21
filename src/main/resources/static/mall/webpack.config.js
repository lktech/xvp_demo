'use strict'

var path = require('path');
var webpack = require("webpack");
var ExtractTextPlugin = require("extract-text-webpack-plugin");
var isProduction = function() {
    return process.env.NODE_ENV === 'production';
}

//webpack插件
var plugins = [
    //提公用js到common.js文件中
    new webpack.optimize.CommonsChunkPlugin('common.js'),
    //将样式统一发布到style.css中
    new ExtractTextPlugin("style.css", {
        allChunks: true,
        disable: false
    }),
    // 使用 ProvidePlugin 加载使用率高的依赖库
    new webpack.ProvidePlugin({
        $: 'jquery'
    })
];
//var entry = ['babel-polyfill','./src/main'],
var entry = ['./src/main'],
    cdnPrefix = "/mall",
    buildPath = "/dist/",
    publishPath = cdnPrefix + buildPath;
//生产环境js压缩和图片cdn
if (isProduction()) {
    //plugins.push(new webpack.optimize.UglifyJsPlugin());
    cdnPrefix = "";
    publishPath = cdnPrefix;
}
//编译输出路径
module.exports = {
    debug: true,
    entry: entry,
    output: {
        path: __dirname + buildPath,
        filename: 'build.js',
        publicPath: publishPath,
        chunkFilename:"[id].build.js?[chunkhash]"
    },
    module: {
        loaders: [{
            test: /\.vue$/,
            loader: 'vue-loader',
        }, {
            test: /\.less$/,
            loader: ExtractTextPlugin.extract(
                "style-loader", 'css-loader?sourceMap!less-loader!cssnext-loader')
        }, {
            test: /\.css$/,
            loader: ExtractTextPlugin.extract(
                "style-loader", "css-loader?sourceMap!cssnext-loader")
        }, {
            test: /\.js$/,
            exclude: /node_modules|vue\/dist/,
            loader: 'babel'
        },{
            test: /\.(jpg|png|gif)$/,
            loader: "file-loader?name=images/[hash].[ext]"
        },
        {
            test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
            loader: 'file'
        }, {
            test: /\.json$/,
            loader: 'json'
        }, {
            test: /\.(html|tpl)$/,
            loader: 'html-loader'
        }]
    },
    babel: {
        presets: ['es2015', 'stage-0'],
        plugins: ['transform-runtime']
    },
    resolve: {
        // require时省略的扩展名，如：require('module') 不需要module.js
        extension: ['', '.js', '.less','.vue'],
        //别名
        alias: {
            filter: path.join(__dirname, 'src/filters'),
            'vue$': 'vue/dist/vue.js'
        }
    },
    plugins: plugins,
    devtool: '#source-map'
};
