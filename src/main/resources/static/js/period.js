//如何動態抓ajax url?
// 載入
function init() {
	$.ajax({
		url: "http://localhost:8080/period/all", // 資料請求的網址
		type: "GET", // GET | POST | PUT | DELETE | PATCH
		// data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
		dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
		success: function(data) {
			// console.log(data);
			let list_html = "";
			$.each(data, function(index, item) {
				list_html += `
                <tr data-periodid="${item.periodID}">
                    <td>${item.periodID}</td>
                    <td>${item.planPeriod}</td>
					<td><a href="update?periodID=${item.periodID}">
					<span class="sl_btn_chakan" style="background-color: #9ac972">修改</span>
					</a></td>
                    <td><input id="del" class="sl_btn_chakan" type="submit" value="刪除"></td>
                </tr>
            `;
			});
			$(".period_list").html(list_html);
			$('#example4').DataTable();
		}
	});
}

// enter送出
$("input#planPeriod").on("keyup", function(e) {
	if (e.which == 13) {
		//enter也等於按按鍵
		$("button#task_add, button#task_update").click();
	}
});

// ====新增====
$("button#task_add").on("click", function() {
	let task_text = $("input#planPeriod").val().trim();
	if (task_text != "") {

		if (!$(this).hasClass("-disabled")) {
			let form_data = {
				"planPeriod": task_text
			};

			$.ajax({
				url: "http://localhost:8080/period/adding", // 資料請求的網址
				type: "POST", // GET | POST | PUT | DELETE | PATCH
				// data: form_data, // 將物件資料(不用雙引號) 傳送到指定的 url
				contentType: "application/json",
				data: JSON.stringify(form_data),
				dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
				statusCode: {                 // 狀態碼
					400: function(res) {
						// alert('輸入格式不正確');
					},
					404: function(res) {
					},
					500: function(res) {
						alert('server error');
					}
				},
				success: function(item) {
					alert('新增成功');
					window.location.href = 'add';
				},
				error: function(xhr) {         // request 發生錯誤的話執行
					if (xhr.status === 400) {
						var errorMessage = xhr.responseText;
						alert(errorMessage);
					} else {
						alert('連線異常');
					}
				}
			});
		}
	} else {
		alert("請輸入訂購期間");
	}
});

// ====刪除====
$(document).on("click", "input#del", function() {
	let r = confirm("是否確認刪除？");
	if (r) {
		let periodID = $(this).closest('tr').data('periodid');
		let that = this;
		$.ajax({
			url: "http://localhost:8080/period/deleting",           // 資料請求的網址
			type: "DELETE",
			contentType: "application/json", // Set the content type to JSON
			data: JSON.stringify({ "periodID": periodID }), // Convert the data to JSON                  // GET | POST | PUT | DELETE | PATCH
			dataType: "text",             // 預期會接收到回傳資料的格式： json | xml | html
			beforeSend: function() {       // 在 request 發送之前執行
			},
			success: function (data) {
                if (data == 'deleted successfully') {
                    alert(data);

                    $(that).closest('tr').animate({
                        "opacity": 0
                    }, 1000, "swing", function () {
                        $(this).remove();
                    });
                } else {
                    alert(data);
                }
            },
			error: function(xhr) {         // request 發生錯誤的話執行
				alert('連線異常！')
			},
			complete: function() {
			}
		});
	}
});

// ====更新====
$("button#task_update").on("click", function() {
	let update_text = $("input#planPeriod")
		.val()
		.trim();
	// console.log($("input#wayID").val());
	// console.log(update_text);
	if (update_text == "") {
		alert("請輸入訂購期間！");
	} else {
		let update_data = {
			"periodID": $("input#periodID").val(),
			"planPeriod": update_text
		}
		$.ajax({
			url: "http://localhost:8080/period/updating",           // 資料請求的網址
			type: "PUT",                  // GET | POST | PUT | DELETE | PATCH
			contentType: "application/json",
			data: JSON.stringify(update_data),
			dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			statusCode: {                 // 狀態碼
				400: function(res) {
					// alert('輸入格式不正確');
				},
				404: function(res) {
				},
				500: function(res) {
					alert('server error');
				}
			},
			success: function(data) {//第一層子元素為li標籤
				alert('更改成功！');
				window.location.href = '/furelise/period/';
			},
			error: function(xhr) {         // request 發生錯誤的話執行
				if (xhr.status === 400) {
					var errorMessage = xhr.responseText;
					alert(errorMessage);
				} else {
					alert('連線異常');
				}
			}
		});
	}
});
