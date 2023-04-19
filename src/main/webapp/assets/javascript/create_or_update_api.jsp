<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<script>
    function formSubmit(data, api_url, method, message1, message2){
        // Tạo XMLHttpRequest
        var xhr = new XMLHttpRequest();

        //Thiết lập api và phương thức
        xhr.open(method, api_url, true);
        // Thiết lập header cho request
        xhr.setRequestHeader("Content-Type", "application/json");
        // Gửi đối tượng JSON
        xhr.send(JSON.stringify(data));

        // Xử lý response nếu cần
        xhr.onreadystatechange = function() {
            if(this.readyState == XMLHttpRequest.DONE){
                if (this.status == 200) {
                    if(method === 'POST')
                        alert(message1)
                    else if(method === 'PUT')
                        alert(message2)
                    //location.reload();//Tải lại trang
                    alert(this.responseText)
                }
                else if(this.status == 406){
                    alert("Không thể cập nhật trạng thái!")
                }
                else if(this.status == 403){
                    alert(xhr.responseText)
                }
            }
        };
    }

    //Khi bấm enter, ngăn form không submit đến url được khai báo và đồng thời thực hiện gửi json
    document.addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            formSubmit();
        }
    });
</script>