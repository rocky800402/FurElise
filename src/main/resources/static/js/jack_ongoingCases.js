$(document).ready(function () {
    $(".headerPage").load("/header.html");
    $(".footerPage").load("/footer.html");
    $(".empSidebarPage").load("/empSidebar.html");

    $('.jack_button_return').click(function () {
        $('#exampleModal2').modal('show');
    });


    document.getElementById('jack-upload').addEventListener('change', function (event) {
        const fileInput = event.target;
        const previewImage = document.getElementById('jack-preview');

        // 確認選擇了檔案
        if (fileInput.files && fileInput.files[0]) {
            const reader = new FileReader();

            // 讀取並顯示圖片
            reader.onload = function (e) {
                previewImage.src = e.target.result;
                previewImage.style.display = 'block'; // 顯示圖片
            };

            reader.readAsDataURL(fileInput.files[0]);
        }
    });

    function showReportIssuePopup() {
        // 取得浮動框元素
        var popup = document.querySelector('.jack_report_issue_popup');
        // 顯示浮動框
        popup.style.display = 'block';
    }

    function hideReportIssuePopup() {
        // 取得浮動框元素
        var popup = document.querySelector('.jack_report_issue_popup');
        // 隱藏浮動框
        popup.style.display = 'none';
    }


});

