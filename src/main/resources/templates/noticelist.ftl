<!DOCTYPE html>
<html>
	<head>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

        <link rel="stylesheet" type="text/css" href="/jqueryDataTables/jquery.dataTables.min.css"/>
        <script type="text/javascript" src="/jqueryDataTables/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="/jqueryTooltipsy/tooltipsy.min.js"></script>
        <title>Smatching Admin Page</title>
	</head>

    <body>
        <div style="text-align:center"><h3><u><font color="#5F04B4" size="5">Smatching Admin Page : ${currentTime}</u></font></h3></div>
        <div style="text-align:right; color:blue;"><a href="/admin/notices/add" onclick="window.open(this.href+location.search, 'newwin', 'width=700,height=800,scrollbars=yes,resizable=yes,left=500,top=50');return false;">새로운 지원사업 공고 추가하기</a></div>
        <div style="text-align:left;"><label><input type="checkbox" id="invalidcheckbox"> 비활성화 된 공고 숨기기<br><br><br></label></div>

        <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>등록시간</th>
                    <th>제목</th>
                    <th>기관</th>
                    <th>부서</th>
                    <th>마감일</th>
                    <th>조회수</th>
                    <th>링크</th>
                    <th>자세히</th>
                </tr>
            </thead>
            <tbody>
                <#list noticeList as notice>
                <tr<#if notice.valid?number == 0> class="invalidRow" style="background-color: #ffa197;"<#elseif notice.notfit?number==1> class="etcnotice" style="background-color: #fffe97;"</#if>>
                    <td>${notice.noticeIdx}</td>
                    <td>${notice.timestamp}</td>
                    <td>${notice.title}</td>
                    <td>${notice.institution}</td>
                    <td>${notice.part}</td>
                    <td>${notice.end_date}</td>
                    <td>${notice.readCnt}</td>
                    <td><span class="message-tooltipsy" style="color: blue;"
                              title="<a href='${notice.refer_url}' target='_blank'>참고 URL로 이동</a><br><br><a href='${notice.origin_url}' target=_'blank'>원본 URL로 이동</a>">Links</span></td>
                    <td><a href="/admin/notices/detail/${notice.noticeIdx}" onclick="window.open(this.href, 'newwin', 'width=600,height=800,scrollbars=yes,resizable=yes,left=500,top=50');return false;">Click</a></td>
                </tr>
                </#list>
            </tbody>
        </table>
    </body>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#example').DataTable( {
                "order": [[0, 'desc']] // 초기값은 0번째 컬럼 내림차순으로 정렬
            } );

            $('td').attr('align', 'center'); // 표 내용들 가운데정렬

            $(window).resize(); // 보기 좋게 브라우저 리사이즈 실행
        } );


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


        $("#invalidcheckbox").click(function() {
            if(this.checked) {
                $(".invalidRow").hide();
            }
            else {
                $(".invalidRow").show();
            }
        });



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
    </script>
</html>
