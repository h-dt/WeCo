import Navbar from "../components/Navbar";
import styled from "styled-components";
import { FaClipboardList } from "react-icons/fa";

const MyPostDiv = styled.div`
    padding: 150px 5% 0px 5%;
`
const MyPostTitle = styled.h1`
    font-size:32px;
`

function MyPost(){
    return (
        <>
        <Navbar/>
        <MyPostDiv>
            <MyPostTitle>
                <FaClipboardList style={{marginRight: "10px"}}/>
                내 글 목록
            </MyPostTitle>
        </MyPostDiv>
        </>
    )
}

export default MyPost;