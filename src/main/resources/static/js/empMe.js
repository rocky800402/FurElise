const getEmpMe = async () => {
    return $.ajax({
        url: `${API_PATH}/auth/emp/me`,
        type: "GET",
        xhrFields: {
            withCredentials: true
        },
        dataType: "json"
    })
}
$(document).ready(async () => {
    let mem
    let emp
    try {
        
         emp = await getEmpMe()
    } catch (error) {
        $("#logoutSection").attr("class", "hidden");
//        if(!window.location.href.includes('login')){
//            location.replace('/login')
//        }
    }
   

    if(emp){
        console.log(emp);
        $(".emp_mem_name").text(emp.empName);
        $("#loginSection").attr("class", "hidden");
    }
        
});
