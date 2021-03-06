function Validator(option){
    var select=document.querySelector(option.form)
    //Hàm thực hiện validate: báo lỗi nhập
    function validate(inputEle,ruleEle){
        var errorEle= inputEle.parentElement.querySelector('.form-message');           
        var errorMess=ruleEle.test(inputEle.value);
        if(errorMess){
            errorEle.innerText=errorMess
            errorEle.classList.add('invalid')    
        }else {
            errorEle.innerText='';
            errorEle.classList.remove('invalid')
        }
    }
    if(select){
        option.rules.forEach(function(ruleEle){
            var inputEle=select.querySelector(ruleEle.selector);
            

            if(inputEle){
                
                inputEle.onblur=function(){
                    //console.log(ruleEle.test(inputEle.value))
                    validate(inputEle,ruleEle);
                }

                inputEle.oninput=function(){
                    var errorEle= inputEle.parentElement.querySelector('.form-message');           
                    errorEle.innerText='';
                    errorEle.classList.remove('invalid')
                }
            }
        })
    }
}
Validator.isRequired=function(selector){
    return{
        selector: selector,
        test: function(value) {
            return value? undefined:'Vui lòng nhập trường này!!'
        }
    }
}
Validator.isEmail=function(selector){
    return {
        selector: selector,
        test: function(value){
            var regex=/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
            return regex.test(value)?undefined:'Không hợp lệ'
        }
    }
}
Validator.minLeng=function(selector, num){
    return {
        selector: selector,
        test: function(value){
            return (value.length>=num)? undefined:('Nhập đủ '+num+' kí tự')
        }
    }
}
Validator.confirmMes=function(selector,selector2, message){
    return{
        selector:selector,
        test: function(value){
            var selectPas=document.querySelector(selector)
            var selectPasfirm=document.querySelector(selector2)
            return (selectPas.value==selectPasfirm.value)?undefined : message
        }
    }
}
