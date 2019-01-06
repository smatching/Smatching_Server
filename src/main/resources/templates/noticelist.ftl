<!DOCTYPE html>
<html>
	<head>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

        <link rel="stylesheet" type="text/css" href="/jqueryDataTables/jquery.dataTables.min.css"/>
        <script type="text/javascript" src="/jqueryDataTables/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="/jqueryTooltipsy/tooltipsy.min.js"></script>
        
        <script type="text/javascript">
        $(document).ready(function() {
            $('#example').DataTable( {
                "order": [[0, 'desc']] // 초기값은 0번째 컬럼 내림차순으로 정렬
            } );
        } );
		</script>
        
        <title>Smatching Admin Page</title>
	</head>
	<body>
		
    <div style="text-align:center"><h3><u><font color="#5F04B4" size="5">Smatching Admin Page</u></font></h3></div>
    <div style="text-align:right; color:blue;"><a href="/admin/notices/add" onclick="window.open(this.href+location.search, 'newwin', 'width=700,height=800,scrollbars=yes,resizable=yes,left=500,top=50');return false;"><h5>새로운 지원사업 공고 추가하기</h5></a><br></div>
    
    
    
    <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>순서번호</th>
                <th>스매칭 등록시간</th>
                <th>제목</th>
                <th>기관</th>
                <th>종료일</th>
                <th>조회수</th>
                <th>유효여부</th>
                <th>기타여부</th>
            </tr>
        </thead>
        <tbody>

            <tr>
                <td>${titles}</td>
                <td>2019-01-06 03:30:11</td>
                <td><font color="#04B404"><strong class="message-tooltipsy" title="메세지">제목제목(마우스오버)</strong></font></td>
                <td><a href="{{result.reportFileLink}}" target="_blank">기관기관(링크)</a></td>
                <td>2019-12-22</td>
                <td><a href="/edittestresult?resultID={{result.id}}" onclick="window.open(this.href, 'newwin', 'width=600,height=800,scrollbars=yes,resizable=yes,left=500,top=50');return false;">팝업팝업</a></td> <!--- 팝업차단 켜져있으면 예외등록 해줘야함 --->
                <td><a href="javascript:void(0);" onclick="deleteTestResult({{result.id}}); return false;">자바스크립트실행</a></td>
                <td>if문넣자</td>
            </tr>

        </tbody>
    </table>
	</body>
    
    <script>    
    function deleteTestResult(resultID) {
        if(confirm("해당 정보를 DB에서 삭제하시겠습니까?") == true) {
            $.ajax({
                url : "deleteresult",
                data : {'resultID':resultID},
                type : "post",
                error : function(error) { 
                    alert("삭제 실패 - Ajax 통신 실패"); 
                },
                success : function(response) {
                    alert("삭제되었습니다."); 
                    location.reload();
                }
            })
        }
        
        else {
            return;
        }
    }
    
    $(".message-tooltipsy").tooltipsy({
        delay: 0,
        css: {
        'padding': '10px',
        'max-width': '900px',
        'color': '#303030',
        'background-color': '#ffffff',
        'border': '2px solid #4893BA',
        '-moz-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
        '-webkit-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
        'box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
        'text-shadow': 'none',
        }
    });    
    
    $('td').attr('align', 'center'); // 표 내용들 가운데정렬
    </script>
    
</html>
