import React from "react";
import {Link} from "react-router-dom";
function Navbar() {
    return (
        <div>
            <Link to="/">Grafik </Link>
            <Link to="/kayit">Kayıt</Link>
        </div>
    );
};

export default Navbar;
