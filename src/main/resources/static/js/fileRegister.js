console.log('fileRegister.js Join Success');

document.getElementById('fileTrigger').addEventListener('click',()=>{
   document.getElementById('files').click();
});

const regExp = new RegExp("\.(exe|sh|bat|js|dll|msi)$"); // 실행 파일들
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
   if(regExp.test(fileName)){
      return 0;
   }else if(fileSize > maxSize){
      return 0;
   }else{
      return 1;
   }; 
};

document.addEventListener('change', (e)=>{
   if(e.target.id == 'files'){
      const fileObjectArray = document.getElementById('files').files;
      // input 태그의 multiple : 배열
      console.log(fileObjectArray);

      const div = document.getElementById('fileZone');

      div.innerHTML = "";
      // 이전에 파일 업로드를 했던 파일들이 있다면 초기화(제거)

      document.getElementsByClassName('fileRegisterButton').disabled = false;
      // 한번 true가 되었다면 false로 복구(초기화)

      let isOK = 1; // isOK : 모든 파일의 검증 결과
      let ul = `<ul class="list-group list-group-flush"><hr>`;
         for(let file of fileObjectArray){
            let validResult = fileValidation(file.name, file.size); // validResult : 개별 파일의 검증 결과
            isOK *= validResult; // 각 파일의 검증 결과를 isOK에 중첩

            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${validResult ? '<div class="text-success-emphasis" style="color: #28a745 !important;">업로드 가능</div>' :
            '<div class="text-danger-emphasis" style="color: #dc3545 !important;">업로드 불가능</div>'}`;
            ul += `${file.name}</div>`;
            ul += `<span style="color: ${validResult ? '#28a745' : '#dc3545'} !important;">${file.size}Byte</span>`;
            ul += `</li><hr>`;
         };
      ul += `</ul>`;
      div.innerHTML = ul;

      if(isOK == 0){ // 파일 중 validation을 통과하지 못한 것이 있다면...
         document.getElementsByClassName('fileRegisterButton').disabled = true; // 버튼 비활성화
      };
   };
});