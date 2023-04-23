<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    function hideOrPublic(statusInt, data, api_url){
        // Tạo XMLHttpRequest
        var xhr = new XMLHttpRequest();

        // Thiết lập phương thức DELETE và URL của API để gửi JSON
        xhr.open("DELETE", api_url, true);
        // Thiết lập header cho request
        xhr.setRequestHeader("Content-Type", "application/json");

        // Gửi đối tượng JSON
        xhr.send(JSON.stringify(data));

        // Xử lý response nếu cần
        xhr.onreadystatechange = function() {
            if(this.readyState == XMLHttpRequest.DONE){
                if (this.status == 200) {
                    alert("Cập nhật trạng thái thành công!")
                    location.reload();//Tải lại trang
                }
                else{
                    alert("Không thể cập nhật trạng thái!");
                }
            }
        };
    }
</script>