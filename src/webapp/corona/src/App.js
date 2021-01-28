import React from "react"
import { Route, Switch } from "react-router-dom"

import Rapor from "./components/Rapor"
import Kayit from "./components/Kayit"
import Navbar from "./components/Navbar"

function App() {
    return (
        <main>
            <Navbar />
            <Switch>
                <Route path="/" component={Rapor} exact />
                <Route path="/kayit" component={Kayit} />
            </Switch>
        </main>
    )
}
export default App