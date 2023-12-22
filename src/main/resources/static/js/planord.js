// 載入
function init() {
    $.ajax({
        url: "http://localhost:8080/planorddto/finding", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        // data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            // console.log(data);
            let list_html = "";
            $.each(data, function (index, item) {
                list_html += `
                <tr data-planordid="${item.planOrdID}">
                    <td>${item.planOrdID}</td>
                    <td>${item.memName}</td>
                    <td>${item.planName}</td>
                    <td>${item.planStart}</td>
                    <td>${item.planEnd}</td>
                    <td>${item.total}</td>
                    <td>${item.planStatus}</td>
					<td><a href="detail?planOrdID=${item.planOrdID} "><span class="sl_btn_chakan" style="background-color: #9ac972">查看詳情</span></a></td>
                </tr>
            `;
            });
            $(".planord_list").html(list_html);
            $('#example4').DataTable();
        }
    });
}

// enter送出
$("input#planName, input#liter, input#planPrice, input#planPricePerCase").on("keyup", function (e) {
    if (e.which == 13) {
        //enter也等於按按鍵
        $("button#task_add, button#task_update").click();
    }
});


// ====更新====
$("button#task_update").on("click", function () {
    let planName = $("input#planName").val().trim();
	let liter = $("input#liter").val().trim();
	let planPrice = $("input#planPrice").val().trim();
	let planPricePerCase = $("input#planPricePerCase").val().trim();
	let times = $("#times").val();
	let planUpload = $("input#planUpload").val();
    if (planName !== "" && liter !== "" && planPrice !== "" && planPricePerCase !== "") {
    
        let update_data = {
            "planID" : $("input#planID").val(), 
            "planName": planName,
	            "liter": liter,
	            "planPrice": planPrice,
	            "planPricePerCase": planPricePerCase,
	            "times": times,
	            "planUpload": planUpload
        }
        $.ajax({
            url: "http://localhost:8080/planord/updating",           // 資料請求的網址
            type: "PUT",                  // GET | POST | PUT | DELETE | PATCH
            // data: { "wayID": wayID, "wayName": wayName },                // 將物件資料(不用雙引號) 傳送到指定的 url
            contentType: "application/json",
            data: JSON.stringify(update_data),
            dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
            beforeSend: function () {       // 在 request 發送之前執行
            },

            success: function (data) {//第一層子元素為li標籤
                alert('更改成功！');
                window.location.href = '/furelise/plan/';
            },

            complete: function () {
            }
        });
    } else {
		alert('請填寫所有欄位');
	}
});
