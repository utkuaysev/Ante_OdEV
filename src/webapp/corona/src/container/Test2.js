import React, { Component } from 'react';
import axios from "axios";
import CanvasJSReact from "../assets/canvasjs.react/canvasjs.react";
const CanvasJSChart = CanvasJSReact.CanvasJSChart;

const xValueFormatString = "DD MMM"
const yValueFormatString = "#,###"
const type = "spline"
const animationEnabled = true
const text= "Korona Grafiği"
const y_title = "Sayı"
var data =[{
    xValueFormatString: "DD MMM",
    yValueFormatString: "#,###",
    type: "spline",
    showInLegend: true,
    name: "",
    dataPoints: []
}]

var options = {
    animationEnabled: animationEnabled,
    title: {
        text: text
    },
    axisY: {
        title: y_title,
    },
    data: data
}

class Test2 extends Component {
    constructor(props) {
        super(props);
        this.state = {options: options}
        this.get_data = this.get_data.bind(this);
    }
    get_data() {
        let checkArray = [];
        for (var key in this.state) {
            if (this.state[key] === true) {
                checkArray.push(key);
            }
        }

        axios.post("http://localhost:8080/checker",checkArray).then(res => {
            const data_points = res.data["dataPoints"].map(data_point => {
                const ar = data_point["date"].split(".")
                return {x: new Date(ar[2], ar[1], ar[0]), y: data_point["y"]}
            })
            const data = [{
                name:"test",
                xValueFormatString: xValueFormatString,
                yValueFormatString: yValueFormatString,
                type: type,
                dataPoints: data_points
            }]
            const options = {
                animationEnabled: animationEnabled,
                title: {
                    text: text
                },
                axisY: {
                    title: y_title,
                },
                data: data
            }
            this.setState({options:options})
        }, err => {
            alert("Server rejected response with: " + err);
        })
    }

    // Checkbox Initial State
    state = {
        vaka: false,
        vefat: false,
        iyilesen: false,
    };

    // React Checkboxes onChange Methods
    onChangeVaka = () => {
        this.setState(initialState => ({
            vaka: !initialState.vaka,
        }));
    }

    onChangeVefat = () => {
        this.setState(initialState => ({
            vefat: !initialState.vefat,
        }));
    }

    onChangeIyilesen = () => {
        this.setState(initialState => ({
            iyilesen: !initialState.iyilesen,
        }));
    }

    // Submit
    onSubmit = (e) => {
        e.preventDefault();
        this.get_data()
    }

    render() {
        return (
            <div className="Test2">
                <h2>Grafiğini görmek istediğiniz veri türlerini seçiniz</h2>
                <form onSubmit={this.onSubmit}>
                    <div className="form-check">
                        <label className="form-check-label">
                            <input type="checkbox"
                                   checked={this.state.vaka}
                                   onChange={this.onChangeVaka}
                                   className="form-check-input"
                            />
                            Vaka
                        </label>
                    </div>

                    <div className="form-check">
                        <label className="form-check-label">
                            <input type="checkbox"
                                   checked={this.state.iyilesen}
                                   onChange={this.onChangeIyilesen}
                                   className="form-check-input"
                            />
                            İyileşen
                        </label>
                    </div>

                    <div className="form-check">
                        <label className="form-check-label">
                            <input type="checkbox"
                                   checked={this.state.vefat}
                                   onChange={this.onChangeVefat}
                                   className="form-check-input"
                            />
                            Vefat
                        </label>
                    </div>
                </form>
                <CanvasJSChart options={this.state.options}
                />
            </div>
        );
    }
}

export default Test2;