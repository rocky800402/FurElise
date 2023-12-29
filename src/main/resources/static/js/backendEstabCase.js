
$(document).ready(function() {
    $('#t1').DataTable( {
        "searching": true, // 預設為true 搜尋功能，若要開啟不用特別設定
        "paging": true, // 預設為true 分頁功能，若要開啟不用特別設定
        "ordering": true, // 預設為true 排序功能，若要開啟不用特別設定
        "sPaginationType": "full_numbers", // 分頁樣式 預設為"full_numbers"，若需其他樣式才需設定
        "lengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]], //顯示筆數設定 預設為[10, 25, 50, 100]
        "pageLength":'5',// 預設為'10'，若需更改初始每頁顯示筆數，才需設定
        "processing": false, // 預設為false 是否要顯示當前資料處理狀態資訊
        "serverSide": false, // 預設為false 是否透過Server端處理分頁…等
        "stateSave": true, // 預設為false 在頁面刷新時，是否要保存當前表格資料與狀態
        "destroy": true, // 預設為false 是否銷毀當前暫存資料
        "info": true, // 預設為true　是否要顯示"目前有 x  筆資料"
        "autoWidth": false, // 預設為true　設置是否要自動調整表格寬度(false代表不要自適應)　　　　
        "scrollCollapse": true, // 預設為false 是否開始滾軸功能控制X、Y軸
        "scrollY": "267.55px", // 若有設置為Y軸(垂直)最大高度
        "dom": 'lrtip',// 設置搜尋div、頁碼div...等基本位置/外觀..等，詳細可看官網
        "ajax": {
            "url": `${API_BASE_URL}/backend-estab-case`,           // 資料請求的網址
            "type": "GET",                  // GET | POST | PUT | DELETE | PATCH
            "dataSrc": "",
            "data":{
                'limit':100,
                'page': 1,
                'takeStatus':false

            }
        },
        "columns": [
            { data: 'estabCaseID'},
            { data: 'estabCaseDate'},
            { data: 'timeRange'},
            { data: 'cityName'},
            { data: 'dispatchStatus',
                // className: `${?'estabCaseTakeStatus':'estabCaseTakeStatus'}`,
                // defaultContent: `'<div>${?"派送中":"未派送"}</div>'`
                "render":function(data) {
                    
                   return data?`<div style="color: white; background-color: #FFC2A0; text-align: center;">派送中</div>`
                   :`<div style="color: white; background-color: #A7D87D; text-align: center;"">未派送</div>`;
                  
   
            }
            },
            { data: 'empID'},
            { data: 'planPricePerCase'},
            {
                data: 'estabCaseID',
                "render":function(data) {
                    
                    return `<a href="/backendEstabCaseSave.html?estabCaseID=${data}" class="btn btn-outline-success mb_estabCase_Class" style="color:#ffffff ; background-color:#A7D87D ; border-color: #A7D87D; margin: 0; text-decoration:none;" >詳情</a>`;
                
            }
            
            }
        ]
    } );


    $('#t2').DataTable( {
        "searching": true, // 預設為true 搜尋功能，若要開啟不用特別設定
        "paging": true, // 預設為true 分頁功能，若要開啟不用特別設定
        "ordering": true, // 預設為true 排序功能，若要開啟不用特別設定
        "sPaginationType": "full_numbers", // 分頁樣式 預設為"full_numbers"，若需其他樣式才需設定
        "lengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]], //顯示筆數設定 預設為[10, 25, 50, 100]
        "pageLength":'5',// 預設為'10'，若需更改初始每頁顯示筆數，才需設定
        "processing": false, // 預設為false 是否要顯示當前資料處理狀態資訊
        "serverSide": false, // 預設為false 是否透過Server端處理分頁…等
        "stateSave": true, // 預設為false 在頁面刷新時，是否要保存當前表格資料與狀態
        "destroy": true, // 預設為false 是否銷毀當前暫存資料
        "info": true, // 預設為true　是否要顯示"目前有 x  筆資料"
        "autoWidth": true, // 預設為true　設置是否要自動調整表格寬度(false代表不要自適應)　　　　
        "scrollCollapse": true, // 預設為false 是否開始滾軸功能控制X、Y軸
        "scrollY": "267.55px", // 若有設置為Y軸(垂直)最大高度
        "dom": 'lrtip',// 設置搜尋div、頁碼div...等基本位置/外觀..等，詳細可看官網
        "ajax": {
            "url": `${API_BASE_URL}/backend-estab-case/dispatch`,           // 資料請求的網址
            "type": "GET",                  // GET | POST | PUT | DELETE | PATCH
            "dataSrc": "",
            "data":{
                'limit':100,
                'page': 1

            }
        },
        "columns": [
            { data: 'estabCaseID'},
            { data: 'estabCaseDate'},
            { data: 'timeRange'},
            { data: 'cityName'},
            { data: 'takeStatus',
                // className: `${?'estabCaseTakeStatus':'estabCaseTakeStatus'}`,
                // defaultContent: `'<div>${?"派送中":"未派送"}</div>'`
                "render":function(data) {
                    
                   return data?`<div style="color: white; background-color:#A7D87D ; text-align: center;">已接單</div>`
                   :`<div style="color: white; background-color: #FFC2A0; text-align: center;"">未接單</div>`;
                  
   
            }
            },
            { data: 'empID'},
            { data: 'planPricePerCase'},
            {
                data: 'estabCaseID',
                "render":function(data) {
                    
                    return `<a href="/backendEstabCaseSave.html?estabCaseID=${data}" class="btn btn-outline-success mb_estabCase_Class" style="color:#ffffff ; background-color:#A7D87D ; border-color: #A7D87D; margin: 0; text-decoration:none;" >詳情</a>`;
                
            }
            
            }
        ]
    } );

    $('#t3').DataTable( {
        "searching": true, // 預設為true 搜尋功能，若要開啟不用特別設定
        "paging": true, // 預設為true 分頁功能，若要開啟不用特別設定
        "ordering": true, // 預設為true 排序功能，若要開啟不用特別設定
        "sPaginationType": "full_numbers", // 分頁樣式 預設為"full_numbers"，若需其他樣式才需設定
        "lengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]], //顯示筆數設定 預設為[10, 25, 50, 100]
        "pageLength":'5',// 預設為'10'，若需更改初始每頁顯示筆數，才需設定
        "processing": false, // 預設為false 是否要顯示當前資料處理狀態資訊
        "serverSide": false, // 預設為false 是否透過Server端處理分頁…等
        "stateSave": true, // 預設為false 在頁面刷新時，是否要保存當前表格資料與狀態
        "destroy": true, // 預設為false 是否銷毀當前暫存資料
        "info": true, // 預設為true　是否要顯示"目前有 x  筆資料"
        "autoWidth": true, // 預設為true　設置是否要自動調整表格寬度(false代表不要自適應)　　　　
        "scrollCollapse": true, // 預設為false 是否開始滾軸功能控制X、Y軸
        "scrollY": "267.55px", // 若有設置為Y軸(垂直)最大高度
        "dom": 'lrtip',// 設置搜尋div、頁碼div...等基本位置/外觀..等，詳細可看官網
        "ajax": {
            "url": `${API_BASE_URL}/backend-estab-case/status`,           // 資料請求的網址
            "type": "GET",                  // GET | POST | PUT | DELETE | PATCH
            "dataSrc": "",
            "data":{
                'limit':100,
                'page': 1,
                'takeStatus':true

            }
        },
        "columns": [
            { data: 'estabCaseID'},
            { data: 'estabCaseDate'},
            { data: 'timeRange'},
            { data: 'cityName'},
            { data: 'estabCaseStatus',
                // className: `${?'estabCaseTakeStatus':'estabCaseTakeStatus'}`,
                // defaultContent: `'<div>${?"派送中":"未派送"}</div>'`
                "render":function(data) {
                    if(data==1){
                        return `<div style="color: white; background-color:#A7D87D ; text-align: center;">完成收取</div>`;
                    }else if(data==2){
                        return `<div style="color: white; background-color: #FFC2A0; text-align: center;"">逾時</div>`;
                    }else{
                        return `<div style="color: white; background-color: #FE9A9A; text-align: center;"">已取消</div>`;
                    }
   
            }
            },
            { data: 'empID'},
            { data: 'planPricePerCase'},
            {
                data: 'estabCaseID',
                "render":function(data) {
                    
                    return `<a href="/backendEstabCaseSave.html?estabCaseID=${data}" class="btn btn-outline-success mb_estabCase_Class" style="color:#ffffff ; background-color:#A7D87D ; border-color: #A7D87D; margin: 0; text-decoration:none;" >詳情</a>`;
                
            }
            
            }
        ]
    } );
} );

