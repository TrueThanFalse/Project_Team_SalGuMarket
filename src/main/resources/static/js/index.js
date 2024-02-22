console.log('index.js Join Success');

document.addEventListener("DOMContentLoaded", function() {
    // 모든 .set-bg 요소를 찾습니다.
    document.querySelectorAll('.set-bg').forEach(function(item) {
        // 데이터 속성에서 이미지 URL을 가져옵니다.
        const imageUrl = item.getAttribute('data-setbg');
        
        // 이미지를 로드하기 위한 새로운 Image 객체를 생성합니다.
        const img = new Image();
        img.onload = function() {
            // 로드 성공 시, 배경 이미지로 설정합니다.
            item.style.backgroundImage = `url('${imageUrl}')`;
        };
        img.onerror = function() {
            // 로드 실패 시, 대체 이미지로 설정합니다.
            item.style.backgroundImage = "url('/img/categories/cat-1.jpg')";
        };
        img.src = imageUrl;
    });
});