import React from "react";
import axios from "axios";

class Kayit extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('Gönderilen değer: ' + this.state.value);
        let val = this.state.value
        event.preventDefault();
        axios.post("http://localhost:8080/haber", {val}).then(res => {
            alert("Başarıyla kaydedildi.")
            this.setState({value: ''});

        }, err => {
            alert("Formatta hata var.Haberler de üç anahtar " +
                "kelimede (Tarih, İl, vaka, vefat, taburcu) ayrı ayrı cümlelerde bulunmalı." + err);
        })
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <h2>
                    Haber
                </h2>
                <textarea rows="8" value={this.state.value} onChange={this.handleChange}/>
                <div>
                    <input type="submit" value="Gönder"/>
                </div>
            </form>
        );
    }
}

export default Kayit