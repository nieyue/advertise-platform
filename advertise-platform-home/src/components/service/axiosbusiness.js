/**
 * axios封装的业务增、删、改、查
 */
//let $this=Vue.prototype;
export default {
    /**
     * 获取列表
     * $this  vue组件
     * p.countUrl 数量url
     * p.listUrl 列表url
     * p.data 返回列表
     * params 请求参数
     * params.total 总数
     * p.success 成功回调
     */
    getList($this,p,params) {
        //过滤属性值为null、''、undefined的属性
        let filterParams=(obj)=>{
            let _newPar = {};
            for (let key in obj) {
                //如果对象属性的值不为空，就保存该属性（这里我做了限制，如果属性的值为0，保存该属性。如果属性的值全部是空格，属于为空。）
                if ((obj[key] === 0 || obj[key]) && obj[key].toString().replace(/(^\s*)|(\s*$)/g, '') !== '') {
                    //记录属性
                    _newPar[key] = obj[key];
                }
            }
            //返回对象
            return _newPar;
        }
      $this.params=filterParams(params);
       params=$this.params;
      // console.log(p.data)
        //获取
        $this.axios({
            method:"post",
            url:p.countUrl,
            data:$this.Qs.stringify(params),
            withCredentials: true
        }).
        then(res => {
            //console.error(res.data.data[0])
            params.total=res.data.data[0];
            if(params.total<=0 ){
                $this.$Message.info('暂无更多')
                $this[p.data]=[]
                return ;
            }
             $this.axios({
                 method:"post",
                 url:p.listUrl,
                 data:$this.Qs.stringify(params),
                 withCredentials: true
                 }).
                 then(res => {
                 //console.log(res)
                 if (res.data.code == 200) {
                     //变量list代替所有
                     $this[p.data]=res.data.data;
                    // console.log($this[p.data])
                     if(typeof p.success=='function'){
                         p.success( $this[p.data]);
                        }
                    } else {
                        $this.$Message.error(res.data.msg)
                    }
                }).catch(res => {
                   // console.log(res)
                    $this.$Message.error('系统异常')
                })
            }).catch(res => {
               // console.log(222222)
                    $this.$Message.error('系统异常')
                })
   },
    /**
     * 获取单个
     * $this  vue组件
     * p.url 列表url
     * p.data 返回列表
     * p.success 成功回调
     */
    get($this,p) {
        //获取
        $this.axios({
            method:"post",
            url:p.url,
            withCredentials: true
            }).
            then(res => {
            console.log(res)
            if (res.data.code == 200) {
                console.log(res.data)
                //变量data代替所有
                $this[p.data]=res.data.data[0];
                if(typeof p.success=='function'){
                    p.success(res);
                }
            } else {
            $this.$Message.error(res.data.msg)
            }
            }).catch(res => {
            $this.$Message.error('系统异常')
            })
   },
    /**
     * 增加
     * $this  vue组件
     * p.ref 验证
     * p.url 增加url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.addModel 界面模型显示隐藏
     * p.success 成功回调
     */
    add($this,p){
        let u=()=>{
            $this[p.loading] = true
            $this.axios({
              method:"post",
              url:p.url,
              data:$this.Qs.stringify($this[p.requestObject]),
              withCredentials: true
              }).then(res => {
                if (res.data.code === 200) {
                    $this[p.showModel] = false
                    $this.$refs[p.ref].resetFields()
                    if(typeof p.success=='function'){
                        p.success();
                    }else{
                        $this.getList()
                    }
              } else {
                $this.$Message.error(res.data.msg)
              }
              $this[p.loading]  = false
            }).catch(res => {
                $this.$Message.error(res.data.msg)
                $this[p.loading]  = false
            })
        }
         //不存在就默认验证成功，即不需验证
         if(!p.ref){
            u();
        }else{
            $this.$refs[p.ref].validate((valid) => {
                if (valid) {
                    u();
                } else {
                        $this.$Message.error('验证失败')
                }
              })
        }
    },
    /**
     * 修改
     * $this  vue组件
     * p.ref 验证
     * p.url 修改url
     * p.requestObject 请求参数对象
     * p.loading loading
     * p.showModel 界面模型显示隐藏
     * p.success 成功回调
     */
    update($this,p){
        let u=()=>{
            $this[p.loading] = true
            $this.axios({
                method:"post",
                url:p.url,
                data:$this.Qs.stringify($this[p.requestObject]),
                withCredentials: true
            }).then(res => {
            if (res.data.code === 200) {
                $this[p.showModel] = false
                $this.$Message.success(res.data.msg)
                if(typeof p.success=='function'){
                    p.success();
                }else{
                    $this.getList()
                }
            }else {
                $this.$Message.error(res.data.msg)
            }
                $this[p.loading] = false
          }).catch(res => {
                $this.$Message.error(res.data.msg)
                $this[p.loading] = false
          })
        }
        //不存在就默认验证成功，即不需验证
        if(!p.ref){
            u();
        }else{
            $this.$refs[p.ref].validate((valid) => {
                if (valid) {
                    u();
                } else {
                        $this.$Message.error('验证失败')
                }
              })
        }
    },
    /**
     * 删除
     * $this  vue组件
     * p.url 修改url
     * p.requestObject 请求参数对象
     * p.success 成功回调
     */
    delete($this,p){
        $this.$Modal.confirm({
            title: '删除',
            content: "确定删除吗？",
            onOk: () => {
              $this.axios({
                      method:"post",
                      url:p.url,
                      data:$this.Qs.stringify($this[p.requestObject]),
                      withCredentials: true
                      }).then(res => {
                      if (res.data.code === 200) {
                        $this.$Message.success(res.data.msg)
                        if(typeof p.success=='function'){
                            p.success();
                        }else{
                            $this.getList()
                        }
                      }else {
                        $this.$Message.error(res.data.msg)
                      }
                    }).catch(res => {
                        $this.$Message.error(res.data.msg)
                    })
                },
            onCancel: () => {
                $this.$Message.info('取消成功');
            }
        })
    },
     /**
     * post发送请求
     * $this  vue组件
     * p.url 修改url
     * p.title 名称
     * p.content 请求内容
     * p.requestObject 请求参数对象
     * p.success 成功回调
     */
    post($this,p) {
        $this.$Modal.confirm({
            title: p.title||'请求',
            content:  p.content||"确定请求吗？",
            onOk: () => {
              $this.axios({
                      method:"post",
                      url:p.url,
                      data:$this.Qs.stringify($this[p.requestObject]),
                      withCredentials: true
                      }).then(res => {
                      if (res.data.code === 200) {
                        $this.$Message.success(res.data.msg)
                        if(typeof p.success=='function'){
                            p.success();
                        }else{
                            $this.getList()
                        }
                      }else {
                        $this.$Message.error(res.data.msg)
                      }
                    }).catch(res => {
                        $this.$Message.error(res.data.msg)
                    })
                },
            onCancel: () => {
                $this.$Message.info('取消成功');
            }
        })
   },
 }