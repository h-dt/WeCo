import { BrowserRouter, Routes, Route} from "react-router-dom";
import Home from './Routes/Home'
import Login from './Routes/Login'
import Write from './Routes/Write'  
import Sign from './Routes/Sign'
import { Info } from "./Routes/Info";
function Router(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/sign" element={<Sign/>}/>
                <Route path="/write" element={<Write/>}/>
                <Route path="/info/:id" element={<Info/>}/>
            </Routes>
        </BrowserRouter>
        
    )
}
export default Router;