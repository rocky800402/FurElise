function init() {
    const urlParams = new URLSearchParams(location.search);
    console.log(urlParams);
    $.ajax({
        url: `${API_BASE_URL}/backend-estab-case/Detail/${urlParams.get('estabCaseID')}`,           // 資料請求的網址
        type: "GET",                  // GET | POST | PUT | DELETE | PATCH
        xhrFields: {
            withCredentials: true
        },
        // data:,            // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function () {       // 在 request 發送之前執行
        },
        success: function (data) {      // request 成功取得回應後執行
        let list_html =`
                <div class="mb_div_left">
                    <div class="form-group">
                        <label for="收取人員">收取人員</label>
                        
                        <input type="text" class="form-control" id="收取人員" value="${data.empName}" readonly>
                        
                    </div>
    
                    <div class="form-group">
                        <label for="案件ID">案件ID</label>
                        <input type="text" class="form-control" id="estabCaseID" value="${data.estabCaseID}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="收取日期">收取日期</label>
                        <input type="date" class="form-control mb_estabCaseDate" id="datepicker" value="${data.estabCaseDate}">
                        <div id="errorMessage"></div>
                        <button type="button" class="btn btn-success btn-block mb_btn_03" style="background-color: #A7D87D; border-color:#A7D87D">重新派單</button>
                    </div>
    
                    <div class="form-group">
                        <label for="收取區間">收取區間</label>
                        <input type="text" class="form-control" id="收取區間" value="${data.timeRange}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="聯絡人">聯絡人</label>
                        <input type="text" class="form-control" id="聯絡人" value="${data.contact}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="連絡電話">連絡電話</label>
                        <input type="text" class="form-control" id="連絡電話" value="${data.contactTel}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="郵遞區號">郵遞區號</label>
                        <input type="text" class="form-control" id="郵遞區號" value="${data.cityCode}${data.cityName}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="街道與樓層">街道與樓層</label>
                        <input type="text" class="form-control" id="街道與樓層" value="${data.floor}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="擺放位置">擺放位置</label>
                        <input type="text" class="form-control" id="擺放位置" value="${data.wayName}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="案件狀態">案件狀態</label>
                        <input type="text" class="form-control" id="案件狀態" value="${estabCaseStatus(data.estabCaseStatus)}" readonly>
                    </div>

                    <div class="form-group">
                    <label for="接單狀態">接單狀態</label>
                    <input type="text" class="form-control" id="接單狀態" value="${data.takeStatus?'已接單':'未接單'}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="此單金額">此單金額</label>
                        <input type="text" class="form-control" id="此單金額" value="${data.planPricePerCase}" readonly>
                    </div>
                </div>
    
                <div class="mb_div_right">
                    <div class="form-group">
                        <label for="客戶反映">客戶反映</label>
                        <textarea class="form-control mb_feedback_mem" id="客戶反映" readonly>無反映事件</textarea>
                    </div>
    
                    <div class="form-group">
                        <label for="夥伴反映">夥伴反映</label>
                        <textarea class="form-control mb_feedback_emp" id="夥伴反映" readonly>無反映事件</textarea>
                    </div>
                
                
    
                    <div class="form-group">
                        <label for="案件完成時間">案件完成時間</label>
                        <input type="text" class="form-control" id="案件完成時間" value="${data.estabCaseEnd!=null?data.estabCaseEnd:'尚未完成'}" readonly>
                    </div>
    
                    <div class="form-group">
                        <label for="執行狀況回復">執行狀況回復</label>
                        <div id="estabCasePic"></div>
                    </div>
    
                    
    
                    <!-- 按鈕 -->
                    <div class="mb_div">
                        
                            <button type="button" class="btn btn-danger btn-block mb_btn_01" style="background-color: #A7D87D; border-color:#A7D87D">回上一頁</button>
                      
                            
                        
                            
                        </div>
                    </div>
                </div>

        `;
        function estabCaseStatus(){
            // console.log(data.estabCaseStatus);
            switch(data.estabCaseStatus){
                case 0:
                    return "待收取";                             
                case 1:
                    return "完成收取";                                       
                case 2:
                    return "逾時";                                    
                case 3:
                    return "已取消";                                      
            }
        }
        function complaintsMem(){
            $.each(data.complaints, function (index, item){
                if(item.empID!=null){
                    $(".mb_feedback_emp").text(item.comDetail);
                }
                if(item.memID!=null){
                    $(".mb_feedback_mem").text(item.comDetail);
                }
            });
            
        }
        function estabCasePic(){
            if(data.estabCasePic!=null){
                $("#estabCasePic").html(`<img alt="kp-1" src="data: image/jpeg;base64,${data.estabCasePic}"/>`)
            }
        }
        function removeReadonly(){
            if(data.estabCaseStatus!=0){
                $('#datepicker').attr("readonly",true);
                $(".mb_btn_03").attr("style","display: none;");
            }
            
        }

        $("div.mb_div_main").html(list_html);

        const qVaStart = $( "#datepicker" )
        const today = dayjs().add(1,'day')
        const afterTomorrow = today.format('YYYY-MM-DD')
        qVaStart.attr("min", afterTomorrow)
        complaintsMem();
        estabCasePic();
        removeReadonly();

        },
        error: function (xhr) {         // request 發生錯誤的話執行
            console.log(xhr);
        },
        complete: function (xhr) {      // request 完成之後執行(在 success / error 事件之後執行)
            // console.log(xhr);

        }
    });
}

$(function () {
init();


$(document).on("click", ".mb_btn_01", function () {
    window.history.back(-1);
});

$(document).on("click", ".mb_btn_03", function () {

    let estabCaseDate= $("#datepicker").val();
    let setabID = $("#estabCaseID").val();
    let form_data = {
        "estabCaseDate": estabCaseDate,
        "estabCaseID": parseInt(setabID)
    };
    console.log(estabCaseDate);
    console.log(setabID);
    console.log(form_data);

        $.ajax({
            url: `${API_BASE_URL}/backend-estab-case`,           // 資料請求的網址
            type: "PATCH",                  // GET | POST | PUT | DELETE | PATCH
            xhrFields: {
                withCredentials: true
            },
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(form_data),  // 將物件資料(不用雙引號) 傳送到指定的 url
            dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
            beforeSend: function () {       // 在 request 發送之前執行
            },
            success: function (data) {      // request 成功取得回應後執行
            },
            error: function (xhr) {         // request 發生錯誤的話執行 
                console.log('xhr');
                console.log(xhr);
                let html_error=`
                <p style="color:red;">${xhr.responseJSON.message}</p>
                `;
                $("#errorMessage").html(html_error);
            },
            complete: function (xhr) {      // request 完成之後執行(在 success / error 事件之後執行)
                // console.log(xhr);
            //     location.reload();
            }
        });

    });
});

function removeReadonly(){
    $('#datepicker').attr("readonly",true);
}



