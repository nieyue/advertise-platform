/**
 * 业务方法
 */
export default {
    
    /*
    ** 判断是否超级管理员，是就显示账户管理
    **
    */
    getIsSuperAdmin(){
        let isSuperAdmin=false;
        if(sessionStorage.getItem("account")){
            let account=JSON.parse(sessionStorage.getItem("account"));
            if(account.role.name=="超级管理员"){
                isSuperAdmin=true;
            }
        } 
        return isSuperAdmin;
    },
    /*
    ** 获取账户
    **
    */
    getAccount(){
        let account=JSON.parse(sessionStorage.getItem("account"));
        return account;
    }
    
}