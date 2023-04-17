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
            if (this.readyState == 4 && this.status == 200) {
                if(statusInt == 1)
                    alert("Đã công khai thể loại này!")
                else
                    alert("Đã ẩn thể loại này!")
                location.reload();//Tải lại trang
            }
        };
    }
</script>