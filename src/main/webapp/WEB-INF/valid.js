/**
 * Created by Anastasia on 06.04.2017.
 */
    function  send() {
    var valid = true;
    var elems = document.forms[0].elements;
    for (var i=0; i<document.forms[0].length;i++){
        if(elems[i].getAttribute('type')=='text'||
            elems[i].tagName=='TEXTAREA'){
            if(elems[i].value==''){
                elems[i].style.border='2px solid red';
                valid=false;
            }
        }
    }
    if(!valid){
        alert('Все поля должны быть заполнены.');
        return false;
    }
    else  return true;
}
