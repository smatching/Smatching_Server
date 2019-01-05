<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>Add Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <style>.radio-inline+.radio-inline, .checkbox-inline+.checkbox-inline { margin-left: 0px; }</style>
</head>

<body>
<div class="container">         
    <div id="signupbox" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
        
            <!-- 상단 폼 제목 -->
            <div class="panel-heading">
                <div class="panel-title"><center>지원 사업 추가 ${titles}</center></div>
            </div>  
            
            <!-- 폼 내용 -->
            <div class="panel-body" >
                <form> <!-- 폼이 두번 들어가야 모양이 이쁨.. -->                        
                    <form class="form-horizontal" id="myform">
                    
                        <!-- 제목 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">지원사업 제목<span class="asteriskField">*</span></label>
                            <div class="controls col-md-8">
                                <input class="input-md textinput textInput form-control" name="title" placeholder="지원사업 제목" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div>
                        
                        
                        <!-- 지역 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">지역</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <!-- 전국 체크박스 만들고 제이쿼리로 컨트롤 -->
                                <label class="radio-inline"><input type="checkbox" name="location" value="seoul" style="margin-bottom: 10px"> 서울</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="busan" style="margin-bottom: 10px"> 부산</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="daegu" style="margin-bottom: 10px"> 대구</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="incheon" style="margin-bottom: 10px"> 인천</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="gwangju" style="margin-bottom: 10px"> 광주</label>
                                
                                <label class="radio-inline"><input type="checkbox" name="location" value="daejeon" style="margin-bottom: 10px"> 대전</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="ulsan" style="margin-bottom: 10px"> 울산</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="sejong" style="margin-bottom: 10px"> 세종</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="gangwon" style="margin-bottom: 10px"> 강원</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="kyunggi" style="margin-bottom: 10px"> 경기</label>
                                
                                <label class="radio-inline"><input type="checkbox" name="location" value="kyungnam" style="margin-bottom: 10px"> 경남</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="kyungbuk" style="margin-bottom: 10px"> 경북</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="jeonnam" style="margin-bottom: 10px"> 전남</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="jeonbuk" style="margin-bottom: 10px"> 전북</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="chungnam" style="margin-bottom: 10px"> 충남</label>
                                
                                <label class="radio-inline"><input type="checkbox" name="location" value="chungbuk" style="margin-bottom: 10px"> 충북</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="jeju" style="margin-bottom: 10px"> 제주</label>
                                <label class="radio-inline"><input type="checkbox" name="location" value="aborad" style="margin-bottom: 10px"> 국외</label>
                            </div>
                        </div> 
                        
                        
                        <!-- 나이 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">나이<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8"  style="margin-bottom: 10px">
                                 <label class="radio-inline"> <input type="checkbox" name="age" value="twenty_less" style="margin-bottom: 10px"> 만 20세 미만</label>
                                 <label class="radio-inline"> <input type="checkbox" name="age" value="twenty_forty" style="margin-bottom: 10px"> 만 20세 이상 ~ 39세 이하</label>
                                 <label class="radio-inline"> <input type="checkbox" name="age" value="forty_more" style="margin-bottom: 10px"> 만 40세 이상</label>
                            </div>
                        </div>
                        
                        
                        <!-- 설립 경과 년수 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">설립 경과 년수</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <!-- 전체 체크박스 만들고 제이쿼리로 컨트롤 -->
                                <label class="radio-inline"><input type="checkbox" name="period" value="zero_one" style="margin-bottom: 10px"> 0~1년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="one_two" style="margin-bottom: 10px"> 1~2년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="two_three" style="margin-bottom: 10px"> 2~3년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="three_four" style="margin-bottom: 10px"> 3~4년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="four_five" style="margin-bottom: 10px"> 4~5년</label>
                                
                                <label class="radio-inline"><input type="checkbox" name="period" value="five_six" style="margin-bottom: 10px"> 5~6년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="six_seven" style="margin-bottom: 10px"> 6~7년</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="seven_more" style="margin-bottom: 10px"> 7년 이상</label>
                                <label class="radio-inline"><input type="checkbox" name="period" value="yet" style="margin-bottom: 10px"> 예비창업자</label>
                            </div>
                        </div>
                        
                        
                        <!-- 설립 경과 년수 -->
                        <div class="form-group required">
                            <label class="control-label col-md-4 requiredField">기업 형태</span></label>
                            <div class="controls col-md-8" style="margin-bottom: 10px">
                                <!-- 전체 체크박스 만들고 제이쿼리로 컨트롤 -->
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="midsmall" style="margin-bottom: 10px"> 중소기업</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="midbig" style="margin-bottom: 10px"> 중견기업</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="big" style="margin-bottom: 10px"> 대기업</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="sole" style="margin-bottom: 10px"> 1인창조기업</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="small" style="margin-bottom: 10px"> 소상공인</label>
                                
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="tradi" style="margin-bottom: 10px"> 전통시장</label>
                                <label class="radio-inline"><input type="checkbox" name="busiType" value="pre" style="margin-bottom: 10px"> 예비창업자</label>
                            </div>
                        </div> 
                        
                        
                       

                        
                        <div class="form-group"> 
                            <div class="aab controls col-md-4 "></div>
                            <div class="controls col-md-8 ">
                                <input type="button" name="Signup" value="Sign Up with Facebook" class="btn btn btn-primary" id="button-id-signup" />
                            </div>
                        </div> 
                            
                    </form>

                </form>
            </div>
        </div>
    </div> 
</div>
    

<script type="text/javascript">
    // $(document).ready(function() { });

    var multipleOptions = ["location", "age", "period", "field", "advantage", "busiType", "category"];
    var textOptions = ["title", "institution", "end_date", "reg_date", "start_date", "phone", "refer_url", "origin_url", "part", "detail_one", "detail_two", "detail_three"];

    function makeMapFromMultipleOptions(option_name) {
        var result = {};
        $("input:checkbox[name="+ option_name +"]").each(function() {
            result[this.value] = this.checked;
        });
        return result;
    }

    function getValueFromTextOptions(option_name) {
        return $("input[name=" + option_name + "]").val();
    }

    function sendNotice() {
        if(confirm("입력한 정보로 지원사업 공고를 등록하시겠습니까?") == true) {
            var result = {};

            for(var i=0; i<multipleOptions.length; i++) {
                result[multipleOptions[i]] = makeMapFromMultipleOptions(multipleOptions[i]);
            }

            for(var i=0; i<textOptions.length; i++) {
                result[textOptions[i]] = getValueFromTextOptions(textOptions[i]);
            }

            $.ajax({
                url : "/notices",
                type : "POST",
                data : JSON.stringify(result),
                contentType: 'application/json; charset=UTF-8',
                dataType: 'html',

                error : function(error) {
                    alert("삭제 실패 - Ajax 통신 실패");
                },
                success : function(response) {
                    alert("성공");
                    //location.reload();
                }
            })
        }

        else {
            return;
        }
    }


</script>
</body>
</html>
