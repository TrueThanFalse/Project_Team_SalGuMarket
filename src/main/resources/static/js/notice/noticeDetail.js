console.log("noticeDetail js");

document.getElementById(`listBtn`).addEventListener(`click`,()=>{
    // location.href="/board/list";
    location.replace(`/board/list`);
});

document.getElementById(`delBtn`).addEventListener(`click`,()=>{
    document.getElementById(`delForm`).submit();

    const delForm = document.getElementById(`delForm`);
    delForm.bno.remove();
    delForm.setAttribute(`action`,`/board/remove`);
    delForm.setAttribute(`method`,`post`);
    delForm.submit();
});