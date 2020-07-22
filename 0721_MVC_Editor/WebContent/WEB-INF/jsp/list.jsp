<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin-left:10px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th,#bbs table td {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	
	.odd {background:silver}
	
	/* paging */
	
	table tfoot ol.paging {
	    list-style:none;
	}
	
	table tfoot ol.paging li {
	    float:left;
	    margin-right:8px;
	}
	
	table tfoot ol.paging li a {
	    display:block;
	    padding:3px 7px;
	    border:1px solid #00B3DC;
	    color:#2f313e;
	    font-weight:bold;
	}
	
	table tfoot ol.paging li a:hover {
	    background:#00B3DC;
	    color:white;
	    font-weight:bold;
	}
	
	.disable {
	    padding:3px 7px;
	    border:1px solid silver;
	    color:silver;
	}
	
	.now {
	   padding:3px 7px;
	    border:1px solid #ff4aa5;
	    background:#ff4aa5;
	    color:white;
	    font-weight:bold;
	}
		
</style>
<script type="text/javascript">
	function send(cp){
		//현재문서에서 이름이 frm이라는 폼을 찾는다.
		document.frm.type.value = 'write';
		document.frm.cPage.value = cp; //1
		
		document.frm.submit();
	}
	
	function viewData(idx){
		
		document.frm.type.value = 'view';
		document.frm.idx.value = idx; //게시물의 기본키 값
		
		document.frm.submit();
	}
</script>
</head>
<body>
	<div id="bbs">
		<table summary="게시판 목록">
			<caption>게시판 목록</caption>
			<thead>
				<tr class="title">
					<th class="no">번호</th>
					<th class="subject">제목</th>
					<th class="reg">날짜</th>
				</tr>
			</thead>
			<tbody>
		
				<tr>
					<td></td>
					<td style="text-align: left">
						<a href="">
							
						</a>
					</td>
					<td></td>
				</tr>

				<tr>
					<td colspan="3">
						등록된 게시물이 없습니다.
					</td>
				</tr>

			</tbody>
			<tfoot>
                <tr>
                    <td colspan="2">
                        <ol class="paging">

	<li class="disable"> &lt; </li>

<li><a href=""> &lt; </a></li>

	<li class="now">1</li>

	<li><a href="">2</a></li>
	<li><a href="">3</a></li>

<li><a href=""> &gt; </a></li>

<li class="disable"> &gt; </li>
       
	
                              </ol>
                          </td>
						  <td>
							<input type="button" value="글쓰기" />
						  </td>
                      </tr>
                  </tfoot>
		</table>
		
	</div>
	
	<form action="Controller" method="post" name="frm">
		<input type="hidden" name="type"/>
		<input type="hidden" name="cPage" value=""/>
		<input type="hidden" name="idx"/>
	</form>
</body>
</html>
    
    
    
    
    
    
    
    
    
    