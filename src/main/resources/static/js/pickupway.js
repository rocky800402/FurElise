// 載入
function init() {
	$.ajax({
		url: "/pickupway/all", // 資料請求的網址
		type: "GET", // GET | POST | PUT | DELETE | PATCH
		// data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
		dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
		success: function(data) {
			// console.log(data);
			let list_html = "";
			$.each(data, function(index, item) {
				list_html += `
                <tr data-wayid="${item.wayID}">
                    <td>${item.wayID}</td>
                    <td>${item.wayName}</td>
					<td><a href="update?wayID=${item.wayID}"><span class="sl_btn_chakan" style="background-color: #9ac972">修改</span></a></td>
                    <td><input id="del" class="sl_btn_chakan" type="submit" value="刪除"></td>
                </tr>
            `;
			});
			$(".pickupway_list").html(list_html);
			$('#example4').DataTable();
		}
	});
}

// enter送出
$("input#wayName").on("keyup", function(e) {
	if (e.which == 13) {
		//enter也等於按按鍵
		$("button#task_add, button#task_update").click();
	}
});

// ====新增====
$("button#task_add").on("click", function() {
	// console.log($("input#wayName"));
	let task_text = $("input#wayName").val().trim();
	if (task_text != "") {

		if (!$(this).hasClass("-disabled")) {
			let form_data = {
				"wayName": task_text
			};

			$.ajax({
				url: "/pickupway/adding", // 資料請求的網址
				type: "POST", // GET | POST | PUT | DELETE | PATCH
				// data: form_data, // 將物件資料(不用雙引號) 傳送到指定的 url
				contentType: "application/json",
				data: JSON.stringify(form_data),
				dataType: "text", // 預期會接收到回傳資料的格式： json | xml | html
				success: function(item) {
					alert(item);
					if (item == "新增成功") {
						window.location.href = 'add';
					}
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
		alert("請輸入收取方式");
	}
});

// ====刪除====
$(document).on("click", "input#del", function() {
	let r = confirm("是否確認刪除？");
	if (r) {
		let wayID = $(this).closest('tr').data('wayid');
		let that = this;
		$.ajax({
			url: "/pickupway/deleting",           // 資料請求的網址
			type: "DELETE",
			contentType: "application/json", // Set the content type to JSON
			data: JSON.stringify({ "wayID": wayID }), // Convert the data to JSON                  // GET | POST | PUT | DELETE | PATCH
			// data: { "wayID": way_id },                // 將物件資料(不用雙引號) 傳送到指定的 url
			dataType: "text",             // 預期會接收到回傳資料的格式： json | xml | html
			beforeSend: function() {       // 在 request 發送之前執行
			},
			success: function(data) {
				if (data == 'deleted successfully') {
					alert(data);

					$(that).closest('tr').animate({
						"opacity": 0
					}, 1000, "swing", function() {
						$(this).remove();
					});
				} else {
					alert(data);
				}
			},
			error: function(xhr) {         // request 發生錯誤的話執行
				alert("不可刪除！");
			},
			complete: function() {
			}
		});
	}
});

// ====更新====
$("button#task_update").on("click", function() {
	let update_text = $("input#wayName")
		.val()
		.trim();
	// console.log($("input#wayID").val());
	// console.log(update_text);
	if (update_text == "") {
		alert("請輸入收取方式！");
	} else {
		let update_data = {
			"wayID": $("input#wayID").val(),
			"wayName": update_text
		}
		$.ajax({
			url: "/pickupway/updating",           // 資料請求的網址
			type: "PUT",                  // GET | POST | PUT | DELETE | PATCH
			// data: { "wayID": wayID, "wayName": wayName },                // 將物件資料(不用雙引號) 傳送到指定的 url
			contentType: "application/json",
			data: JSON.stringify(update_data),
			dataType: "text",             // 預期會接收到回傳資料的格式： json | xml | html
			beforeSend: function() {       // 在 request 發送之前執行
			},
			success: function(item) {//第一層子元素為li標籤
				alert(item);
				if(item == "更新成功"){
					window.location.href = '/pickupway/';
				}
			},
			complete: function() {
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
