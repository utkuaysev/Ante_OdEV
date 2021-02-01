import React from "react";
import {Link} from "react-router-dom";
import "../style.css"
function Navbar() {
    return (
        <div className="header">
            <div className="header-right">
                <a href="/">Grafik</a>
                <Link to="/kayit">Kayit</Link>
            </div>
        </div>    );
};

export default Navbar;
