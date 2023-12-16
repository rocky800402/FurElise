// 載入

function planName_selector() {
    $.ajax({
        url: "http://localhost:8081/furelise/planord/planname", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        // data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            // data = {planName, planPrice, liter}
            // console.log(data[0][1]);
            let list_html = "";
            $.each(data, function (index, item) {
                list_html += `
                <option value="${item[0]}" data-price="${item[1]}">${item[0]}(${item[2]}公升)</option>
            `;
            });
            $("select[name='planName']").html(list_html);
        }
    });
}

function pickupTime_selector() {
    $.ajax({
        url: "http://localhost:8081/furelise/planord/timerange", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        // data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            // console.log(data);
            let list_html = "";
            $.each(data, function (index, item) {
                list_html += `
                <option value="${item.timeID}">${item.timeRange}</option>
            `;
            });
            $("select#pickupTime").html(list_html);
        }
    });
}

function period_selector() {
    $.ajax({
        url: "http://localhost:8081/furelise/planord/planperiod", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        // data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            // console.log(data);
            let list_html = "";

            $.each(data, function (index, item) {
                let length = 28 * item.planPeriod - 1;
                list_html += `
                <option data-month="${item.planPeriod}" data-length="${length}" value="${item.periodID}">${item.planPeriod}個月</option>
            `;
            });
            $("select#period").html(list_html);
        }
    });
}

function way_selector() {
    $.ajax({
        url: "http://localhost:8081/furelise/planord/wayname", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        // data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            // console.log(data);
            let list_html = "";

            $.each(data, function (index, item) {
                list_html += `
                <option value="${item.wayID}">${item.wayName}</option>
            `;
            });
            $("select#pickupWay").html(list_html);
        }
    });
}

function city_selector() {
    $.ajax({
        url: "http://localhost:8081/furelise/planord/citycode", // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        // data: { user_id: user_id }, // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            // console.log(data);
            let list_html = "";

            $.each(data, function (index, item) {
                list_html += `
                <option value="${item.cityCode}">${item.cityCode}${item.cityName}</option>
            `;
            });
            $("select#cityCode").html(list_html);
        }
    });
}

//新增
$("button#task_add").on("click", function () {
    let planName = $("select[name='planName']").val(); //String planName
    let pickupTime = $("select#pickupTime").val(); //timeID
    let period = $("select#period").val(); //periodID
    let times = $("#times").val(); //String times

    let weekDay = [];
    $.each($("[name='day']:checked"), function () {
        weekDay.push($(this).val());
    }); // String[] weekDay

    let pickupWay = $("select#pickupWay").val(); //wayID
    let planStart = $("input[name='planStart']").val(); //planStart
    let contact = $("input#contact").val().trim(); //contact
    let contactTel = $("input#contactTel").val().trim(); //contactTel
    let cityCode = $("select#cityCode").val(); //cityCode
    let floor = $("input#floor").val().trim(); //floor
    let pickupStop = $("input#pickupStop").val().trim(); //pickupstop

    if (contact !== "" && contactTel !== "" && floor !== "" && pickupStop !== "") {

        if (!$(this).hasClass("-disabled")) {
            let form_data = {
                "planName": planName,
                "timeID": pickupTime,
                "periodID": period,
                "times": times,
                "weekDay": weekDay,
                "wayID": pickupWay,
                "planStart": planStart,
                "contact": contact,
                "contactTel": contactTel,
                "cityCode": cityCode,
                "floor": floor,
                "pickupStop": pickupStop
            };

            $.ajax({
                url: "http://localhost:8081/furelise/planorddto/adding", // 資料請求的網址
                type: "POST", // GET | POST | PUT | DELETE | PATCH
                // data: form_data, // 將物件資料(不用雙引號) 傳送到指定的 url
                contentType: "application/json",
                data: JSON.stringify(form_data),
                dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
                beforeSend: function () {
                    $("button#task_add").addClass("-disabled");
                },

                success: function (item) {
                    alert("訂購完成！");
                    window.location.href = 'intro';
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

