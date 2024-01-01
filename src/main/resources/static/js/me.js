const getMe = async () => {
    return $.ajax({
        url: `${API_PATH}/auth/mem/me`,
        type: "GET",
        xhrFields: {
            withCredentials: true
        },
        dataType: "json"
    })
}

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
         mem = await getMe()
         emp = await getEmpMe()
    } catch (error) {
        $("#logoutSection").attr("class", "hidden");
//        if(!window.location.href.includes('login')){
//            location.replace('/login')
//        }
    }
    if(mem){
        $(".mb_mem_name, .mb-mem-btn").text(mem.memName);
        $("#loginSection").attr("class", "hidden");
    }

    if(emp){
        $(".emp_mem_name").text(emp.empName);
        $("#loginSection").attr("class", "hidden");
    }
        
});


