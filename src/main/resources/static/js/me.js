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

$(document).ready(async () => {
    let mem

    try {
         mem = await getMe()
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
        
});


