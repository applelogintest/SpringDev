<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container" id="root">
	</div>
	<script type="text/babel">
		/*
			<App name="홍길동"/> => new App("홍길동"); => 변경할 수 없다
			props : 변경이 불가
			state : 변경이 가능

			=> <App/>
				1) constructor() : 초기화 (변수 설정)
				2) render() : 데이터를 받아서 화면 출력 (HTML)
				3) componetDidMount() : 완료 => 브라우저에 출력
				   ===================
				완료가 된 다음 => 화면을 변경 ===> render()
							 ========
							   리렌더링 => setState()
				function H(){
				}

				var H=function(){}
				var H=()=>{}
		*/
		// JSX => Javascript + XML (ES6버전 이상)
		const H=()=>{
			const color=['red','green','blue','yellow','pink'];
			let rand=parseInt(Math.random()*5);
			let s={"color":color[rand]};
			return (
				<h1 className="text-center" style={s}>뮤직 Top 200</h1>	
			)
		}
		/*const H2=React.memo(()=>{
			const color=['red','green','blue','yellow','pink'];
			let rand=parseInt(Math.random()*5);
			let s={"color":color[rand]};
			return (
				<h1 className="text-center" style={s}>뮤직 Top 200</h1>	
			)
		});*/
		class App extends React.Component{	
			// 생성자
			constructor(props){
				super(props); // 속성값 받기
				// 서버에서 들어오는 데이터를 받아서 저장
				this.state={
					music:[],
					ss:''
				}
				// react에서 이벤트 등록
				this.handleUserInput=this.handleUserInput.bind(this);
			}

			handleUserInput(ss)
			{
				this.setState({ss:ss});
			}
			
			componentDidMount()
			{
				// 서버 연결
				axios.get('http://localhost/myapp3/main/music.do').then((result)=>{
					console.log(result.data);
					//this.state.music=result.data;
					// setState(){render()}
					this.setState({music:result.data})
				})
			}
			// 화면 출력(HTML)
			render(){
				return(
					<div className="row">
						<H/>
						<SearchBar ss={this.state.ss} onUserInput={this.handleUserInput}/>
						<div style={{"height":"30px"}}></div>
						<MusicTable music={this.state.music} ss={this.state.ss}/>
					</div>
				)
			}
		}

		class MusicTable extends React.Component{
			render(){
				let rows=[];
				// [{title: , mno: , ...},{},{},{},{},{},{},{}...] => var m={a: ,b: } m.a m.b / var m=[] m[0]
				this.props.music.forEach((m,key)=>{
					if(m.title.indexOf(this.props.ss)===-1)
					{
						return;
					}
					rows.push(<MusicRow music={m}/>);
				})
				/*
					for(MusicVO vo:list)
				*/
				return(
					<table className="table">
						<thead>
						    <tr className="danger">
							    <th className="text-center">순위</th>
								<th className="text-center">등폭</th>
								<th className="text-center"></th>
								<th className="text-center">노래명</th>
								<th className="text-center">가수명</th>
						    </tr>
						</thead>
						<tbody>
							{rows}
						</tbody>
					</table>
				)
			}
		}
		// "" 문자열로 인식 변수값은 "" 붙이면 안됨
		class MusicRow extends React.Component{
			render()
			{
				return (
					<tr>
						<td className="text-center">{this.props.music.mno}</td>
						<td className="text-center">
							{/* 다중 조건문  return안에서 주석 주는법*/}
							{
								this.props.music.state==="상승" &&
								<span style={{"color":"red"}}>▲{this.props.music.idcrement}</span>								
							}
							{
								this.props.music.state==="하강" &&
								<span style={{"color":"blue"}}>▼{this.props.music.idcrement}</span>
							}
							{
								this.props.music.state==="유지" &&
								<span style={{"color":"gray"}}>-</span>
							}
						</td>
						<td className="text-center">
							<img src={this.props.music.poster} width="30" height="30"/>
						</td>
						<td><a href={"detail.do?mno="+this.props.music.mno}>{this.props.music.title}</a></td>
						<td>{this.props.music.singer}</td>
					</tr>
				)
			}
		}

		class SearchBar extends React.Component{
			constructor(props)
			{
				super(props);
				this.onChange=this.onChange.bind(this);
			}
			onChange(e)
			{
				this.props.onUserInput(e.target.value); // e.target.value 입력한값을 가져옴
			}
			render(){
				return(
					<input type="text" size="30" className="input-sm" placeholder="검색"
						value={this.props.ss} onChange={this.onChange}
					/>
				)
			}
		}
		ReactDOM.render(<App/>,document.getElementById('root'));
		// String data=new App().render() ==> $('#root').html(data)
	</script>
</body>
</html>