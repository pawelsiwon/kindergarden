import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";

class AddPerson extends Component {
  state = {
    name: "",
    surname: "",
    birthdate: "",

    pesel: "",
    phonenumber: "",
    role: "",

    email: "",
    login: "",
    password: "",

    city: "",
    postale: "",
    street: "",
    streetAddress: "",
    suite: "",

    lista: [
      { title: "Administrator", value: "admin" },
      { title: "Parent", value: "parent" },
      { title: "Employee", value: "employee" }
    ],
    formMessage: "",
    alertType: ""
  };
  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/app")}
          >
            Return
          </button>
        </div>
        <h4 className="mb-3">Add new person</h4>
        <form className="needs-validation">
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">First name</label>
              <input
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.name}
                onChange={e => this.setState({ name: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="lastName">Last name</label>
              <input
                id="lastName"
                className="form-control"
                type="text"
                value={this.state.surname}
                onChange={e => this.setState({ surname: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="birthdate">Birthdate</label>
              <input
                type="date"
                id="birthdate"
                className="form-control"
                value={this.state.birthdate}
                onChange={e => this.setState({ birthdate: e.target.value })}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="001">Pesel</label>
              <input
                type="text"
                id="001"
                className="form-control"
                value={this.state.pesel}
                onChange={e => this.setState({ pesel: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="002">Phone number</label>
              <input
                type="text"
                id="003"
                className="form-control"
                value={this.state.phonenumber}
                onChange={e => this.setState({ phonenumber: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="003">Role</label>
              <select
                type="text"
                id="002"
                className="custom-select d-block w-100"
                value={this.state.role}
                onChange={e => this.setState({ role: e.target.value })}
              >
                <option value="">Choose role...</option>
                {this.state.lista.map(opt => (
                  <option key={opt.value} value={opt.value}>
                    {opt.title}
                  </option>
                ))}
              </select>
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="0001">E-mail</label>
              <input
                type="email"
                className="form-control"
                id="0001"
                value={this.state.email}
                onChange={e => this.setState({ email: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0002">Login</label>
              <input
                type="text"
                className="form-control"
                id="0002"
                value={this.state.login}
                onChange={e => this.setState({ login: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0003">Password</label>
              <input
                type="password"
                className="form-control"
                id="0003"
                value={this.state.password}
                onChange={e => this.setState({ password: e.target.value })}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-3 mb-3">
              <label htmlFor="city">City</label>
              <input
                type="text"
                id="city"
                className="form-control"
                value={this.state.city}
                onChange={e => this.setState({ city: e.target.value })}
              />
            </div>
            <div className="col-md-2 mb-3">
              <label htmlFor="postale">Postale</label>
              <input
                type="text"
                id="postale"
                pattern="[1-9]{2}-[0-9]{3}"
                placeholder="__-___"
                className="form-control"
                value={this.state.postale}
                onChange={e => this.setState({ postale: e.target.value })}
              />
            </div>
            <div className="col-md-5 mb-3">
              <label htmlFor="street">Street</label>
              <input
                type="text"
                id="street"
                className="form-control"
                value={this.state.street}
                onChange={e => this.setState({ street: e.target.value })}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="apartment">Apartment</label>
              <input
                type="text"
                pattern="[1-9]*"
                id="apartment"
                className="form-control"
                value={this.state.streetAddress}
                onChange={e => this.setState({ streetAddress: e.target.value })}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="suite">Suite</label>
              <input
                type="text"
                pattern="[1-9]*[a-z]?"
                id="suite"
                className="form-control"
                value={this.state.suite}
                onChange={e => this.setState({ suite: e.target.value })}
              />
            </div>
          </div>
          <div className="row">
            <button
              className="btn btn-danger col-md-1 m-2"
              onClick={this.resetForm}
            >
              Reset
            </button>
            <button
              className="btn btn-primary col-md-1 m-2"
              onClick={this.submitPerson}
            >
              Add
            </button>
          </div>
          <div
            className={"m-2 alert alert-" + this.state.alertType}
            role="alert"
          >
            {this.state.formMessage}
          </div>
        </form>
      </div>
    );
  }

  resetForm = () => {
    this.setState({
      name: "",
      surname: "",
      birthdate: "",

      pesel: "",
      phonenumber: "",
      role: "",

      email: "",
      login: "",
      password: "",

      city: "",
      postale: "",
      street: "",
      streetAddress: "",
      suite: "",

      lista: [
        { title: "Administrator", value: "admin" },
        { title: "Parent", value: "parent" },
        { title: "Employee", value: "employee" }
      ],
      formMessage: "",
      alertType: ""
    });
  };

  submitPerson = () => {
    console.log(this.props.apiHost + "/person/add");
    const requestData = {
      loginData: this.props.session,
      person: this.state,
      personId: null
    };
    console.log(requestData);
    Axios.post(this.props.apiHost + "/person/add", requestData)
      .then(
        this.setState({
          alertType: "success",
          formMessage: "Person added!"
        })
      )
      .catch(
        this.setState({
          alertType: "warning",
          formMessage:
            "An error occured, please try again or contact with admin!"
        })
      );
  };
}

export default withRouter(AddPerson);
