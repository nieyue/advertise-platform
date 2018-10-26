<!-- 自定义wangeditor -->
<template>
    <div class="body-wrap">
        <div id="editor"  ref="editor"  style="text-align:left"></div>
    </div>
</template>
<script>
  export default {
    name: 'MyWangeditor',
      props:{
      //父组件传默认的值,string 
      content:{
          type:String,
          required:false
      },
  },
    watch: { 
        //获取父组件的数据 
    content: {  
    　　handler(newValue, oldValue) {
        if(!this.editor){
            //动态生成的
            this.init(this.editorId) 
        }
         this.update()
        },
    　　deep: true  
    }
    },
    created(){
        
        window.onload = ()=>{
          this.init(this.editorId)
        }
        },
    data () {
        return {
            editorId:"editor"+parseInt(Math.random()*100),
            editor:null,
      }
    },
    methods: {
        //初始化
        init(editorId){
            this.$refs.editor.id=editorId;
            this.editor=new (this.wangeditor)("#"+editorId);
            //this.editor=new (this.wangeditor)("#editor");
            this.editor.customConfig.zIndex = 100
            this.editor.customConfig.uploadFileName = 'editorUpload'      //给上传的本地图片文件命名的统一名称  
            this.editor.customConfig.uploadImgServer = this.axios.defaults.imgURL+'/tool/img/add'//官方文档上写的是服务器地址，也就是上传图片的方法名
           // this.editor.customConfig.uploadImgServer = 'http://localhost:8080/tool/img/add'//官方文档上写的是服务器地址，也就是上传图片的方法名
            // 将图片大小限制为 2M
            this.editor.customConfig.uploadImgMaxSize = 2 * 1024 * 1024
            // 限制一次最多上传 1 张图片
            this.editor.customConfig.uploadImgMaxLength = 1
            // this.editor.customConfig.uploadImgParams = {
            //     // 如果版本 <=v3.1.0 ，属性值会自动进行 encode ，此处无需 encode
            //     // 如果版本 >=v3.1.1 ，属性值不会自动 encode ，如有需要自己手动 encode
            //     token: 'abcdef12345'
            // }
            this. editor.customConfig.colors = [
        '#000000',
        '#eeece0',
        '#1c487f',
        '#4d80bf',
        '#c24f4a',
        '#8baa4a',
        '#7b5ba1',
        '#46acc8',
        '#f9963b',
        '#ffffff',
        '#ff0000','#eb4310','#f6941d','#fbb417','#ffff00','#cdd541','#99cc33','#3f9337','#219167','#239676','#24998d','#1f9baa','#0080ff','#3366cc','#333399','#003366','#800080','#a1488e','#c71585','#bd2158'
        ]
            this.editor.customConfig.withCredentials = true
            // 将 timeout 时间改为 30s
            this.editor.customConfig.uploadImgTimeout = 30000
            this.editor.customConfig.uploadImgHooks = {
                before: function (xhr, editor, files) {
                    // 图片上传之前触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件
                    
                    // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
                    // return {
                    //     prevent: true,
                    //     msg: '放弃上传'
                    // }
                },
                success: function (xhr, editor, result) {
                    // 图片上传并返回结果，图片插入成功之后触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                },
                fail: function (xhr, editor, result) {
                    // 图片上传并返回结果，但图片插入错误时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                },
                error: function (xhr, editor) {
                    // 图片上传出错时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
                },
                timeout: function (xhr, editor) {
                    // 图片上传超时时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
                },
                // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
                // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
                customInsert: function (insertImg, result, editor) {
                    // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                    // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

                    // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
                    //var url = result.url
                    var url = result
                    insertImg(url)

                    // result 必须是一个 JSON 格式字符串！！！否则报错
                }
            }
            this.editor.customConfig.debug = true
            this.editor.customConfig.onchange = (html) =>{
                this.$emit("getWangEditorContent", html)
            }
            this.editor.create();
            console.log(this.editor)
            this.editor.txt.html("<p>输入内容...</p>") 
          /*  var toolbar=document.querySelector(" .w-e-toolbar");
           toolbar.innerHTML += '<div class="w-e-menu"><a class="_wangEditor_btn_fullscreen"  href="###">全屏</a></div>';
           function hasClass(obj, cls) { 
            return obj.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)')); 
            } 
            function addClass(obj, cls) { 
            if (!hasClass(obj, cls)) obj.className += " " + cls; 
            } 
            
            function removeClass(obj, cls) { 
            if (hasClass(obj, cls)) { 
                var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)'); 
                obj.className = obj.className.replace(reg, ' '); 
            } 
            } 
           function toggleClass(obj,cls){ 
            if(hasClass(obj,cls)){ 
                removeClass(obj, cls); 
            }else{ 
                addClass(obj, cls); 
            } 
            } 
           document.querySelector('._wangEditor_btn_fullscreen').addEventListener("click",function(ev){
                  toggleClass(document.querySelector("#"+editorId ),'fullscreen-editor');
                if(document.querySelector('._wangEditor_btn_fullscreen').innerText == '全屏'){
                    document.querySelector('._wangEditor_btn_fullscreen').innerText='退出全屏';
                }else{
                    document.querySelector('._wangEditor_btn_fullscreen').innerText='全屏';
                }
            });  */

        },
        update(){
           this.editor.txt.html(this.content)
        }
    }
  }
</script>
<style>
.w-e-toolbar {
	flex-wrap: wrap;
	-webkit-box-lines: multiple;
}

.w-e-toolbar .w-e-menu:hover{
	z-index: 10002!important;
}

.w-e-menu a {
	text-decoration: none;
}

.fullscreen-editor {
	position: fixed !important;
	width: 100% !important;
	height: 100% !important;
	left: 0px !important;
	top: 0px !important;
	background-color: white;
	z-index: 9999;
}

.fullscreen-editor .w-e-text-container {
	width: 100% !important;
	height: 95% !important;
}
</style>


