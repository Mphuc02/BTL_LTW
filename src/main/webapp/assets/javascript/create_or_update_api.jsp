<script>
    function formSubmit(data, api_url, method){
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
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
                // if(this.responseText)
                // {
                //     if(!categoryId)
                //         alert("Thêm thể loại thành công!")
                //     else
                //         alert("Cập nhật thành công!")
                // }
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