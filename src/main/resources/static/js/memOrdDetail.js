

const getMemOrd = async () => {
    return $.ajax({
        url: `${API_BASE_URL}/mem-ord`,
        type: "GET",
        xhrFields: {
            withCredentials: true
        },
        dataType: "json"
    })
}

const onCommentClick = (ordIdx, detailIdx) => {
    console.log(memOrds[ordIdx].memOrdDetailBOs[detailIdx])
    const detail = memOrds[ordIdx].memOrdDetailBOs[detailIdx]
    var myModal = new bootstrap.Modal(document.getElementById('detailModal'), {
        keyboard: false
      })
    $('#titleModalLabel').text(detail.pname)
    const commentModalStarHtml = `
        <span class="star mb_star ${detail.level >= 1 ? '-on' : ''}" data-star="1"><i class="fas fa-star " style="width: 30px; height: 30px;"></i></span>
        <span class="star mb_star ${detail.level >= 2 ? '-on' : ''}" data-star="2"><i class="fas fa-star " style="width: 30px; height: 30px;"></i></span>
        <span class="star mb_star ${detail.level >= 3 ? '-on' : ''}" data-star="3"><i class="fas fa-star " style="width: 30px; height: 30px;"></i></span>
        <span class="star mb_star ${detail.level >= 4 ? '-on' : ''}" data-star="4"><i class="fas fa-star " style="width: 30px; height: 30px;"></i></span>
        <span class="star mb_star ${detail.level >= 5 ? '-on' : ''}" data-star="5"><i class="fas fa-star " style="width: 30px; height: 30px;"></i></span>
    `
    $('#commentModalStar').html(commentModalStarHtml)
    if(detail.feedback){
        $('#commentModalContent').attr('disabled', true).val(detail.feedback)
    }else{
        $('#commentModalContent').attr('disabled', false).val("")
    }

    myModal.show()
}

let memOrds = []

$(async function () {
    try {
        memOrds = await getMemOrd()
    } catch (error) {
        console.log(error)
    }
    // console.log('memOrds', memOrds)
    const renderHtml = memOrds.map((ord, index) => 
    `
    <section class="mb_plan_card">
        <div class="yh_ord">
            <div class="ord_detail">
                ${ord.memOrdDetailBOs.map((detail, detailIdx) => 
                    `
                    <div class="ord_product">
                        <img src="data: image/jpeg;base64, ${detail.pimage}" class="ord_detail_pic">
                        <div class="ord_detail_text">
                            <p>${detail.pname}</p>
                            <p style="display: inline-block;">x</p>
                            <p style="display: inline-block;">${detail.detaQty}</p>
                        </div>
                        <td>
                            <button onclick="onCommentClick(${index},${detailIdx})" class="btn mb_estab_case_level_btn rounded-pill"  data-bs-toggle="modal" data-bs-target="#exampleModal${index}${detailIdx}" data-bs-whatever="@mdo" >
                                評價
                            </button>
                        </td>
                        <p class="yh_p_price" style="display: inline-block;">$${detail.pprice}</p>
                    </div>
                    `
                )}
            <div class="ord_status" style="display: inline-block;">
                <div class="ordno">
                <p>訂單編號:</p>
                <p>${ord.ordID}</p>
                <br>
                <p>配送地址:</p>
                <p>${ord.address}</p>
                <br>
                <p>付款方式:</p>
                <p>${ord.payment == 0?'信用卡':'轉帳'}</p>
                <br>
                <p>寄送方式:</p>
                <p>${ord.deliver == 0?'宅配':'超商'}</p>
                <br>
                <p>購買時間:</p>
                <p>${ord.ordDate?dayjs(ord.ordDate).format('YYYY-MM-DD'):''}</p>
                <br>
                <p>出貨時間:</p>
                <p>${ord.deliverDate?dayjs(ord.deliverDate).format('YYYY-MM-DD'):''}</p>
                <br>
                </div>
            </div>
            <div class="ord_total" style="display: inline-block;">
                <p class="total_text">小計:</p>
                <p class="total_price">${ord.sum??0}</p>
                <hr style="margin: 0 0 0 0;">
                <p class="total_text">運費:</p>
                <p class="total_price">${ord.shipping??0}</p>
                <hr style="margin: 0 0 0 0;">
                <p class="total_text">折扣:</p>
                <p class="total_price">${ord.couponDis??0}</p>
                <hr style="margin: 0 0 0 0;">
                <p class="total_text">訂單金額:</p>
                <p class="total_price">${ord.total??0}</p>
            </div>
            </div>
            <div class="mem_ord_detail-div">
            ${
                {
                  0: '<div class="mem_ord_div"><p class="mem_p">處理中</p></div>',
                  1: '<div class="mem_ord_div_02"><p class="mem_p">已寄出</p></div>',
                  2: '<div class="mem_ord_div_01"><p class="mem_p">已送達</p></div>',
                  3: '<div class="mem_ord_div_03"><p class="mem_p">退貨</p></div>',
                  4: '<div class="mem_ord_div_03"><p class="mem_p">已取消</p></div>'
                }[ord.ordStatus]
              }
            <button class="btn btn-primary btn_feedback change_status btn_refund btn_off mb_bnt_close" type="submit">取消訂單</button>
            </div>
        </div>
    </section>
    `
    )
    
    $('.mb_content_main').html(renderHtml)
    console.log("ord",mem);
    
})
