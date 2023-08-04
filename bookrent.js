
const RentDay = document.forms[0].rent
const EndDay = document.forms[0].end
const custId = document.forms[0].id
const bookCode = document.forms[0].code

function DateFormat(vdate){
	let year = vdate.getFullYear()
	let month = (vdate.getMonth()+1).toString().padStart(2,'0')
	let date = vdate.getDate().toString().padStart(2,'0')
	
	result = [year,month,date].join('-')
	return result
}

RentDay.value = DateFormat(new Date())

let endDate = new Date()
endDate.setDate(endDate.getDate()+14)
EndDay.value = DateFormat(endDate)

function rentmessage() {
	alert(`${custId.value} 회원님, 대여도서 [${bookCode.value}] 의 반납 기한 날짜는 ${DateFormat(endDate)} 입니다.`)
}