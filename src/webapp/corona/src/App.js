import React from "react"
import { Route, Switch } from "react-router-dom"

import Grafik from "./components/Grafik"
import Kayit from "./components/Kayit"
import Navbar from "./components/Navbar"

function App() {
    return (
        <main>
            <Navbar />
            <Switch>
                <Route path="/" component={Grafik} exact />
                <Route path="/kayit" component={Kayit} />
            </Switch>
        </main>
    )
}
export default App