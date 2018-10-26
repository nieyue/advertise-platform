<!--自定义上传组件 -->
<template>
    <div class="body-wrap">
        <div class="demo-upload-list" v-for="item in uploadList" >
            <template v-if="item.status === 'finished'">
                <img :src="item.url">
                <div class="demo-upload-list-cover">
                    <Icon type="ios-eye-outline" @click.native="handleView(item)"></Icon>
                    <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
                </div>
            </template>
            <template v-else>
                <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
            </template>
        </div>
        <Upload
            :action="action"
            :name="name"
            ref="upload"
            :show-upload-list="false"
            :default-file-list="uploadList"
            :on-success="handleSuccess"
            :format="['jpg','jpeg','png','mp4']"
            :max-size="2048"
            :on-format-error="handleFormatError"
            :on-exceeded-size="handleMaxSize"
            :before-upload="handleBeforeUpload"
            multiple
            >
              <Button  icon="ios-cloud-upload-outline">上传</Button>
          </Upload> 
          <Modal title="预览" class="img-prev-model" v-model="visible" >
                <img :src="showImgUrl" v-if="visible" style="width: 100%;">
          </Modal>
    </div>
</template>
<script>
export default {
  name: 'MyUpload',
  props:{
      //父组件传默认的值,string
      defaultUpload:{
          type:String,
          required:false
      },
      //父组件传默认的值,array
      defaultUploadList:{
          type:Array,
          required:false
      }
  },
  data () {
    return {
        //显示预览的图片路径
        showImgUrl: '',
        visible: false,
        //子组件传值给父组件
        uploadList: [],
        //路径
        action:this.axios.defaults.imgURL+"/tool/img/add",
        //action:"http://localhost:8080/tool/img/add",
        //上传名称
        name:"editorUpload",
        //最大上传数
        maxNumber:1
    }
  },
  methods: {
        //上传前
        handleBeforeUpload(){
            const check = this.uploadList.length <this.maxNumber;
                if (!check) {
                    this.$Notice.warning({
                        title: '最多上传数目为'+this.maxNumber
                    });
                }
                return check;
        },
        //文件大小超过
        handleMaxSize (file) {
            this.$Notice.warning({
                    title: '文件过大',
                    desc:  file.name + '大小超过2M了'
                });
        },
        //预览
        handleView (item) {
        this.showImgUrl = item.url;
        this.visible = true;
        },
        //删除
        handleRemove (file) {
            this.uploadList.splice(this.uploadList.indexOf(file), 1);
        },
        //上传成功
        handleSuccess (res, file){
          //  console.log(res)
            console.log(this.uploadList)
            file.url = res;
            //传值给父组件
            this.uploadList.push(file)
             this.$emit("uploadList",this.uploadList)
        },
        
        handleFormatError (file) {
            this.$Notice.warning({
                title: '文件格式错误',
                desc:  file.name + ' 格式错误，请上传正确格式'
            });
        }
  },
  created () {
       
      },
      watch: { 
          //获取父组件的数据列表 
        defaultUploadList: {  
        　　handler(newValue, oldValue) {  
            this.uploadList=[]
                newValue.forEach(element => {
                    this.uploadList.push({
                        status:'finished',
                        showProgress:false,
                        percentage:100,
                        url:element
                    })
                })
            },
        　　deep: true  
        },  
              //获取父组件的数据 
        defaultUpload: {  
            handler(newValue, oldValue) {  
            this.uploadList=[]
            //console.log(newValue)
            if(newValue){
                this.uploadList.push({
                    status:'finished',
                    showProgress:false,
                    percentage:100,
                    url:newValue
                })
            }
            },
        　　deep: true  
        }, 
　　} , 
  mounted () {
  }
}
</script>
<style>
    .demo-upload-list{
        display: inline-block;
        width: 60px;
        height: 60px;
        text-align: center;
        line-height: 60px;
        border: 1px solid transparent;
        border-radius: 4px;
        overflow: hidden;
        background: #fff;
        position: relative;
        box-shadow: 0 1px 1px rgba(0,0,0,.2);
        margin-right: 4px;
    }
    .demo-upload-list img{
        width: 100%;
        height: 100%;
    }
    .demo-upload-list-cover{
        display: none;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background: rgba(0,0,0,.6);
    }
    .demo-upload-list:hover .demo-upload-list-cover{
        display: block;
    }
    .demo-upload-list-cover i{
        color: #fff;
        font-size: 20px;
        cursor: pointer;
        margin: 0 2px;
    }
    .img-prev-model *{
        z-index:9999999999
    }
</style>