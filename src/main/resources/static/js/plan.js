// 載入
function init() {
    $.ajax({
        url: "http://localhost:8081/furelise/plan/all", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        // data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            // console.log(data);
            let list_html = "";
            $.each(data, function (index, item) {
                list_html += `
                <tr data-planid="${item.planID}">
                    <td>${item.planID}</td>
                    <td>${item.planName}</td>
                    <td>${item.liter}</td>
                    <td>${item.planPrice}</td>
                    <td>${item.planPricePerCase}</td>
                    <td>${item.times}</td>
					<td><a href="/furelise/plan/update?planID=${item.planID} "><span class="sl_btn_chakan" style="background-color: #9ac972">修改</span></a></td>
                    <td><input id="del" class="sl_btn_chakan" type="submit" value="刪除"></td>
                </tr>
            `;
            });
            $(".plan_list").html(list_html);
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

// ====新增====
$("button#task_add").on("click", function () {
    // console.log($("input#planName"));
    let planName = $("input#planName").val().trim();
	let liter = $("input#liter").val().trim();
	let planPrice = $("input#planPrice").val().trim();
	let planPricePerCase = $("input#planPricePerCase").val().trim();
	let planUpload = $("input#planUpload").val();
	
    if (planName !== "" && liter !== "" && planPrice !== "" && planPricePerCase !== "") {

        if (!$(this).hasClass("-disabled")) {
            let form_data = {
                "planName": planName,
	            "liter": liter,
	            "planPrice": planPrice,
	            "planPricePerCase": planPricePerCase,
	            "planUpload": planUpload
            };

            $.ajax({
                url: "http://localhost:8081/furelise/plan/adding", // 資料請求的網址
                type: "POST", // GET | POST | PUT | DELETE | PATCH
                // data: form_data, // 將物件資料(不用雙引號) 傳送到指定的 url
                contentType: "application/json",
                data: JSON.stringify(form_data),
//                dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
                beforeSend: function () {
                    $("button#task_add").addClass("-disabled");
                },

                success: function (item) {
                    alert("新增成功");
                    window.location.href = 'add'; // Redirect to http://localhost:8081/furelise/plan/add
                },

                complete: function () {
                    $("button.task_add").removeClass("-disabled");
                }
            });
        }
    } else {
        alert("請填寫所有欄位");
    }
});

// ====刪除====
$(document).on("click", "input#del", function () {
    let r = confirm("是否確認刪除？");
    if (r) {
        let planID = $(this).closest('tr').data('planid');
        let that = this;
        $.ajax({
            url: "http://localhost:8081/furelise/plan/deleting",           // 資料請求的網址
            type: "DELETE",
            contentType: "application/json", // Set the content type to JSON
            data: JSON.stringify({ "planID": planID }), // Convert the data to JSON                  // GET | POST | PUT | DELETE | PATCH
            // data: { "wayID": way_id },                // 將物件資料(不用雙引號) 傳送到指定的 url
            dataType: "text",             // 預期會接收到回傳資料的格式： json | xml | html
            beforeSend: function () {       // 在 request 發送之前執行
            },
            success: function (data) {
                alert("刪除成功！");

                $(that).closest('tr').animate({
                    "opacity": 0
                }, 1000, "swing", function () {
                    $(this).remove();
                });
                // 無法遞補
                // $('#example4').DataTable().draw();
            },
            error: function (xhr) {         // request 發生錯誤的話執行
                alert("不可刪除！");
            },
            complete: function () {
            }
        });
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
            url: "http://localhost:8081/furelise/plan/updating",           // 資料請求的網址
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
