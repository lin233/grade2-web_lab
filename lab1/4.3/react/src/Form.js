import React, { Component } from 'react';

import {Table} from 'react-bootstrap';
import {FormControl} from 'react-bootstrap'
import {Label} from 'react-bootstrap'
import {Button} from 'react-bootstrap'
import {Grid,Row,Col} from 'react-bootstrap'
import {Tab,Tabs,Nav,NavItem} from 'react-bootstrap'

import constantData from './bookshop.json'


class Cell extends React.Component{
    constructor(prop){
        super(prop);
        this.state={
            input:false,
            str:this.props.str
        };
        this.changeState=this.changeState.bind(this);
        this.changeContent=this.changeContent.bind(this);
        this.changeIputBlur=this.changeIputBlur.bind(this);
    }

    changeState(){
            this.setState({
                input:true
            });
    }

    changeContent(e){
        this.setState({
            str:e.target.value
        });
    }

    changeIputBlur(){
        this.setState({
            input:false
        });
        if (this.props.cg==="Book"){
            this.props.book[this.props.seq].Name=this.state.str;
        }
        else if (this.props.cg==="Author"){
            this.props.book[this.props.seq].Author=this.state.str;
        }
        else if (this.props.cg==="Language"){
            this.props.book[this.props.seq].Language=this.state.str;
        }
        else if (this.props.cg==="Published"){
            this.props.book[this.props.seq].Published=this.state.str;
        }
        else if (this.props.cg==="Sales"){
            this.props.book[this.props.seq].Sales=this.state.str;
        }
    }
    render(){
        if (!this.state.input){
            return (
                <td onClick={this.changeState}>
                    {this.state.str}
                </td>
            );
        }
        else{
            return(
                <input type="text" placeholder={this.state.str}  onChange={this.changeContent} onBlur={this.changeIputBlur}   />
            );
        }
    }
}


class BookTuple extends React.Component{
    constructor(prop){
        super(prop);
        this.deleteTuple=this.deleteTuple.bind(this);
        this.select=this.select.bind(this);
    }

    select(){
        this.props.select();
    }

    deleteTuple(){
        this.props.delete(this.props.seq);
    }



    render(){
        return(
            <tr>
                <Cell cg="Book" str={this.props.Name} seq={this.props.seq}book={this.props.book}/>
                <Cell cg="Author" str={this.props.Author}  seq={this.props.seq}book={this.props.book}/>
                <Cell cg="Language" str={this.props.Language} seq={this.props.seq}book={this.props.book}/>
                <Cell cg="Published" str={this.props.Published}seq={this.props.seq}book={this.props.book}/>
                <Cell cg="Sales" str={this.props.Sales} seq={this.props.seq}book={this.props.book}/>
                <Button bsStyle="danger" onClick={this.deleteTuple}>X</Button>
            </tr>
        )
    }
}

class ControlledTabs extends React.Component {
    constructor(prop){
        super(prop);
        this.state={
            book:this.props.book,
        };

        this.addBook=this.addBook.bind(this);
        this.deleteState=this.deleteState.bind(this);
    }



    addBook(){
        let temp=this.state.book;
        temp.push({
            "Name": "b1",
            "Author": "Tom",
            "Language": "Chinese",
            "Published":"2001",
            "Sales":"9.99"},);
        temp[temp.length-1].Name="X";
        this.setState({book:temp});
    }

    deleteState(index){
        let temp=this.state.book;
        //temp.splice(index,1);
        delete temp[index];
        this.setState({book:temp});
    }




    render(){
        let list=this.state.book.map((item,i) => (
            <BookTuple key={i+1} seq={i} Name={item.Name} Author={item.Author} Language={item.Language} Published={item.Published} Sales={item.Sales}
                      delete={this.deleteState}book={this.props.book}/>

        ));
        return(
            <Grid>

                <Row className="show-grid">
                    <Col xs={12} md={12}>
                        <Table responsive>
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Author</th>
                                <th>Language</th>
                                <th>Published</th>
                                <th onClick={this.props.sort}>Sales</th>
                            </tr>
                            </thead>
                            <tbody>
                            {list}
                            </tbody>
                            <Button bsStyle="success" onClick={this.props.add}>Add Book</Button>
                        </Table>
                    </Col>

                </Row>
            </Grid>
        );
    }
}

class MainTable extends React.Component{
    constructor(prop){
        super(prop);
        this.state={
            book:constantData.bookshop,
            IfSearch:false,
            IfSort:false,
            searchbook:[],
            searchname:"",
            sortbook:[],
            choose:1
        };

        this.addBook=this.addBook.bind(this);
        this.searchBook=this.searchBook.bind(this);
        this.handlerReturn=this.handlerReturn.bind(this);
        this.deleteState=this.deleteState.bind(this);
        this.Upsort=this.Upsort.bind(this);
        this.Downsort=this.Downsort.bind(this);

    }

    Upsort(){
        let temp=this.state.book;
        let sortTemp = [];
        let loop1 = 0;
        while(temp.length!=0){
            let currentBook = temp[0];
            let currentkey = 0;

            //loop1++;
            temp.map((item,i)=>{
                    if(item.Sales<=currentBook.Sales){
                        currentBook=item;
                        currentkey=i;
                    }
                }
            )
            sortTemp.push(currentBook);

            let midtemp = [];
            temp.map((item,i)=>{
                    if(i!=currentkey){
                        midtemp.push(item);
                    }
                }
            )
            temp = midtemp;
        }

        this.setState({
            sortbook:sortTemp,
            IfSort:!this.state.IfSort,
            choose:3
        });

    }

    Downsort(){
        let temp=this.state.book;
        let sortTemp = [];
        let loop1 = 0;
        while(temp.length!=0){
            let currentBook = temp[0];
            let currentkey = 0;

            //loop1++;
            temp.map((item,i)=>{
                    if(item.Sales>=currentBook.Sales){
                        currentBook=item;
                        currentkey=i;
                    }
                }
            )
            sortTemp.push(currentBook);

            let midtemp = [];
            temp.map((item,i)=>{
                    if(i!=currentkey){
                        midtemp.push(item);
                    }
                }
            )
            temp = midtemp;
        }

        this.setState({
            sortbook:sortTemp,
            IfSort:!this.state.IfSort
        });

    }

    addBook(){
        let temp=this.state.book;
        temp.push({
            "Name": "b",
            "Author": "Tom",
            "Language": "Chinese",
            "Published":"2001",
            "Sales":"9.99"},);
        this.setState({book:temp});
    }


    searchBook(){
        alert(this.state.searchname);
        alert(this.state.IfSearch);
        let temp=this.state.book;
        let searchtemp = [];
        temp.map((item,i)=>{
            if(item.Name==this.state.searchname){
                searchtemp.push(item);
            }
        }
        )

        this.setState(
            {
                IfSearch:true,
                searchbook:searchtemp,
                choose:2

            }
        );
    }

    handleSearch(event){
        this.setState({
            searchname: event.target.value
        })
    }

    handlerReturn(){
        let searchtemp = [];
        this.setState(
            {
                IfSearch:false,
                searchbook:searchtemp,
                book:this.state.book,
                choose:1
            }
        );
    }


    deleteState(index){
        let temp=this.state.book;
        //temp.splice(index,1);
        delete temp[index];
        this.setState({book:temp});
    }

    render(){
        let element=<div><ControlledTabs book={this.state.book}add={this.addBook}sort={this.Upsort}/></div>
        if(this.state.choose===1){
            element=<div><ControlledTabs book={this.state.book}add={this.addBook}sort={this.Upsort}/></div>
        }
        else if(this.state.choose===2){
            element=<div> <ControlledTabs book={this.state.searchbook}add={this.addBook}sort={this.Upsort}/></div>
        }
        else if(this.state.choose===3){
            element=<div> <ControlledTabs book={this.state.sortbook}add={this.addBook}sort={this.Upsort}/></div>

        }
        return(

            <Grid>
                <Row></Row>
                <input
                    value={this.state.searchname}
                    onChange={this.handleSearch.bind(this)} />
                <Button bsStyle="success" onClick={this.searchBook}>Search Book</Button>
                <Button bsStyle="success" onClick={this.handlerReturn}>Return</Button>
                <Button bsStyle="success" onClick={this.Upsort}>Sort</Button>

                <Col>
                    {element}
                </Col>


            </Grid>
        );
    }
}




class Mainbook extends Component    {

    constructor(prop,context){
        super(prop,context);
        this.state={
            book:constantData.book,
            key:1
        };
        this.modifyState=this.modifyState.bind(this);
        this.addTuple=this.addTuple.bind(this);
        this.deleteState=this.deleteState.bind(this);
    }
    modifyState(stateName){
        this.setState(stateName);
    }

    addTuple(){
        let temp=this.state.book;
        temp.push({
            "name": "b",
            "subject": "new",
            "question":[
                {
                    "name": "q",
                    "tag": ["t", "t"],
                    "frequent": 0,
                    "date": " "
                }],
            "quest":[
                { "name": "",
                    "tag": ["",""],
                    "frequent": 0,
                    "date":""}
            ]},);
        this.setState({book:temp});
    }

    deleteState(index){
        alert(index);
        let temp=this.state.book;
        //temp.splice(index,1);
        delete temp[index];
        this.setState({book:temp});
    }

    handleUsernameChange (event) {
        this.setState({
            key: event.target.value
        })
    }
    render () {
        return (
            <div>
                 {
                     this.state.book.map((item,i) =>{
                         return(
                             <Grid>
                             <Row>
                                 <Col xs={12} md={8}><ControlledTabs buk={i} key={i} book={this.state.book[i]} delete={this.deleteState} /></Col>
                             </Row>
                             </Grid>
                        )
                     })}
                     <Button bsStyle="success" onClick={this.addTuple}> + </Button>

                     </div>

                     )
                }
}

//export default ControlledTabs;
export default MainTable;

/* <Col>
        {this.state.IfSort?<div> <ControlledTabs book={this.state.sortbook}sort={this.Upsort}/></div>
            :<div><ControlledTabs book={this.state.book} add={this.addBook}sort={this.Upsort}/></div>
        }
    </Col>

    <Col>
        {this.state.IfSearch?<div> <ControlledTabs book={this.state.searchbook}sort={this.Upsort}/></div>
            :<div><ControlledTabs book={this.state.book}add={this.addBook}sort={this.Upsort}/></div>
    }
    </Col>
 */