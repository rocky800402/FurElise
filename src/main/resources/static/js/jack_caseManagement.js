$(document).ready(function() {
    $('#example').DataTable( {
        /*設定屬性(預設功能)區塊*/
        "searching": false,// 預設為true 搜尋功能，若要開啟不用特別設定
        // "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]], //顯示筆數設定 預設為[10, 25, 50, 100] 用戶在下拉選單中看到的是第二個數組中的文本值，而實際上應用到表格的是第一個數組中對應的數字。
        // "pageLength": '5',// 預設為'10'，若需更改初始每頁顯示筆數，才需設定　
        "ajax": {
            "url": "http://localhost:8080/estabcase/120001/0",           // 資料請求的網址，利用 /0 來做案件狀態分類
            "type": "GET",                  // GET | POST | PUT | DELETE | PATCH
            "dataSrc": "",
            "data": {
                'limit': 100,
                'page': 1,
                'empID': 120001,
                'estabCaseStatus': 1

            }
        },
        columns: [
            {
                data: 'estabCaseID',
            },
            {
                data: null,
                render: function (data, type, row) {
                    return row.estabCaseDate + '<br>' + row.timeRange;
                }
            },
            {
                data: 'cityName',
            },
            {
                data: null,
                render: function (data, type, row) {
                    return '<button class="jack_information">資訊</button>';
                }
            },
            {
                data: null,
                render: function (data, type, row) {
                    // 根据 estabCaseStatus 和 takeStatus 的值返回不同的显示内容
                    if (row.estabCaseStatus === 0) {
                        if (row.takeStatus === false) {
                            return '<span style="color: green;">未派送</span>';
                        } else if (row.takeStatus === true) {
                            return '<span style="color: blue;">執行中</span>';
                        }
                    }
                }
            },
            {
                data: null,
                render: function (data, type, row) {
                    var buttonsHTML = '';

                    if (row.estabCaseStatus === 0) {
                        if (row.takeStatus) {
                            // 如果 estabCaseStatus 為 0 且 takeStatus 為 true，顯示已接單按钮
                            buttonsHTML += '<button class="jack_button_accept update-status-button" data-action="accept" data-id="' + row.estabCaseID + '" disabled>已接</button>';
                        } else {
                            // 不然就顯示接單按钮
                            buttonsHTML += '<button class="jack_button_accept update-status-button" data-action="accept" data-id="' + row.estabCaseID + '">接單</button>';
                        }
                        //按鈕間空格
                        buttonsHTML += ' <span class="button-separator"></span> ';
                    }
                    // 拒單按鈕
                    buttonsHTML += '<button class="jack_button_reject update-status-button" data-action="reject" data-id="' + row.estabCaseID + '">拒單</button>';

                    return buttonsHTML;
                }


            },
        ]
    });



    $('#example1').DataTable( {
        /*設定屬性(預設功能)區塊*/
        "searching": false,// 預設為true 搜尋功能，若要開啟不用特別設定
        // "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]], //顯示筆數設定 預設為[10, 25, 50, 100] 用戶在下拉選單中看到的是第二個數組中的文本值，而實際上應用到表格的是第一個數組中對應的數字。
        // "pageLength": '5',// 預設為'10'，若需更改初始每頁顯示筆數，才需設定　
        "ajax": {
            "url": "http://localhost:8080/estabcase/120001/1",           // 資料請求的網址 目前120001寫死 之後要用empID分類
            "type": "GET",                  // GET | POST | PUT | DELETE | PATCH
            "dataSrc": "",
            "data": {
                'limit': 100,
                'page': 1,
                'empID': 120001,
                'estabCaseStatus': 1

            }
        },
        columns: [
            {
                data: 'estabCaseID',
            },
            {
                data: 'estabCaseDate'
            },
            {
                data: 'cityName',
            },
            {
                data: 'planPricePerCase',
            },
            {
                data: null,
                render: function (data, type, row) {
                    return '<button class="btn-case-info">資訊</button>';
                }
            },
        ]
    });


})

$('#example').on('click', '.update-status-button', function () {

    var action = $(this).data('action');
    var estabCaseID = $(this).data('id');
    updateStatus(action, estabCaseID);
});
function updateStatus(action, estabCaseID) {

    var table = $('#example').DataTable();

    console.log(action);
    console.log(estabCaseID);
    var acceptButton = $('.jack_button_accept.update-status-button[data-id="' + estabCaseID + '"]');
    var rejectButton = $('.jack_button_reject.update-status-button[data-id="' + estabCaseID + '"]');
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080//estabcase/updateCaseStatus',
        data: {
            action: action,
            estabCaseID: estabCaseID
        },
        success: function (response) {

            console.log('Update successful');

            if (response) {
                if (action === 'accept') {
                    acceptButton.text('已接').prop('disabled', true);
                    rejectButton.prop('disabled', true);
                    console.log('Accept logic');
                    table.ajax.reload();//重整table的資料

                } else if (action === 'reject') {

                    rejectButton.text('已拒').prop('disabled', true);
                    acceptButton.prop('disabled', true);
                    console.log('Reject logic');
                    table.ajax.reload();//重整table的資料
                }
            } else {
                console.log('Unexpected success response');
            }
        },
        error: function (error) {
            console.error('Update failed', error);
        }
    });
}
// $(document).ready(function () {
//
//     $('#example').DataTable({
//         /*設定屬性(預設功能)區塊*/
//         "searching": false,// 預設為true 搜尋功能，若要開啟不用特別設定
//         "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]], //顯示筆數設定 預設為[10, 25, 50, 100] 用戶在下拉選單中看到的是第二個數組中的文本值，而實際上應用到表格的是第一個數組中對應的數字。
//         "pageLength":'5'// 預設為'10'，若需更改初始每頁顯示筆數，才需設定　　　
//     })
//     $('#example1').DataTable({
//         /*設定屬性(預設功能)區塊*/
//         "searching": false,// 預設為true 搜尋功能，若要開啟不用特別設定
//         "lengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]], //顯示筆數設定 預設為[10, 25, 50, 100] 用戶在下拉選單中看到的是第二個數組中的文本值，而實際上應用到表格的是第一個數組中對應的數字。
//         "pageLength":'5'// 預設為'10'，若需更改初始每頁顯示筆數，才需設定　　　
//     })


    // var orderStatus = ""; // 用來跟蹤接單狀態，初始為空

    // $(".jack_button_accept").click(function () {
    //     if (orderStatus !== "已接" && orderStatus !== "已拒") {
    //
    //         $(this).text("已接");
    //         $(this).css({
    //             "background-color": "#9ac972"
    //         });
    //
    //
    //         $(".jack_button_reject").css({
    //             "background-color": "#EDEDED",
    //             "cursor": "not-allowed",
    //         });
    //
    //         // 設定接單狀態
    //         orderStatus = "已接";
    //     }
    // });
    //
    // $(".jack_button_reject").click(function () {
    //     if (orderStatus !== "已拒" && orderStatus !== "已接") {
    //
    //         $(this).text("已拒");
    //         $(this).css({
    //             "background-color": "#FE9A9A"
    //         });
    //
    //         $(".jack_button_accept").css({
    //             "background-color": "#EDEDED",
    //             "cursor": "not-allowed"
    //         });
    //
    //         // 設定拒單狀態
    //         orderStatus = "已拒";
    //     }
    // });

    //日歷中文化
//     $.fn.datepicker.dates['zh-CN'] = {
//         days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
//         daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
//         daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
//         months: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
//         monthsShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
//         today: "今日",
//         suffix: [],
//         meridiem: []
//     };
//
//     // Initialize Datepicker
//     $('#datepickerButton').datepicker({
//         language:'zh-CN',
//         format: 'yyyy/mm/dd', // 設定日期格式
//         autoclose: true, // 選擇日期後自動關閉
//         orientation: 'bottom',//日歷顯示相對於按鈕的位置
//     });
//
//     // 預設顯示日期
//     var defaultDate = new Date();
//     var formattedDefaultDate = formatDate(defaultDate);
//     $('#datepickerButton').text(formattedDefaultDate);
//
//     // 監聽日期變更事件，更新按鈕文字
//     $('#datepickerButton').datepicker().on('changeDate', function (e) {
//         var selectedDate = e.format();
//         $('#datepickerButton').text(selectedDate);
//     });
//
//     // 格式化日期為指定格式
//     function formatDate(date) {
//         var dd = date.getDate();
//         var mm = date.getMonth() + 1; // 一月是 0!
//         var yyyy = date.getFullYear();
//         if (dd < 10) {
//             dd = '0' + dd;
//         }
//         if (mm < 10) {
//             mm = '0' + mm;
//         }
//         return yyyy + '/' + mm + '/' + dd;
//     }
// });