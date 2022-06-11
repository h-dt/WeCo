import { BrowserRouter, Routes, Route} from "react-router-dom";
import Home from './Routes/Home'
import Login from './Routes/Login'
import Write from './Routes/Write'  
import Sign from './Routes/Sign'
function Router(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/sign" element={<Sign/>}/>
                <Route path="/write" element={<Write/>}/>
            </Routes>
        </BrowserRouter>
        
    )
}
export default Router;