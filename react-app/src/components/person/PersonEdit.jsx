import React, { Component } from "react";
import Axios from "axios";
import { withRouter } from "react-router";

class PersonEdit extends Component {
  state = {
    message: "",
    alertType: "",

    id: " ",
    birthdate: "",
    city: "",
    email: "",
    login: "",
    name: "",
    password: "",
    pesel: "",
    phonenumber: "",
    postale: "",
    street: "",
    streetAddress: "",
    suite: "",
    surname: "",
    childs: [],
    role: "",
    payments: [],

    lista: [
      { title: "Administrator", value: "admin" },
      { title: "Parent", value: "parent" },
      { title: "Employee", value: "employee" }
    ]
  };

  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/person/show")}
          >
            Show all
          </button>
        </div>
        <div className="row">
          <h3 className="screen-responsive">
            {this.state.name + " " + this.state.surname} details
          </h3>
        </div>
        <form className="needs-validation">
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="name">First name</label>
              <input
                id="name"
                className="form-control"
                type="text"
                value={this.state.name}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="lastName">Last name</label>
              <input
                id="surname"
                className="form-control"
                type="text"
                value={this.state.surname}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="birthdate">Birthdate</label>
              <input
                type="date"
                id="birthdate"
                className="form-control"
                value={this.state.birthdate}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="001">Pesel</label>
              <input
                type="text"
                id="pesel"
                className="form-control"
                value={this.state.pesel}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="002">Phone number</label>
              <input
                type="text"
                id="phonenumber"
                className="form-control"
                value={this.state.phonenumber}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="003">Role</label>
              <select
                type="text"
                id="role"
                className="custom-select d-block w-100"
                value={this.state.role}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              >
                <option value={this.state.role}>{this.state.role}</option>
                {this.state.lista
                  .filter(rola => rola.value.toUpperCase() !== this.state.role)
                  .map(opt => (
                    <option key={opt.value} value={opt.value.toUpperCase()}>
                      {opt.title.toUpperCase()}
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
                id="email"
                value={this.state.email}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0002">Login</label>
              <input
                type="text"
                className="form-control"
                id="login"
                value={this.state.login}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0003">Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                value={this.state.password}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
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
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
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
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-5 mb-3">
              <label htmlFor="street">Street</label>
              <input
                type="text"
                id="street"
                className="form-control"
                value={this.state.street}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="apartment">Apartment</label>
              <input
                type="text"
                pattern="[1-9]*"
                id="streetAddress"
                className="form-control"
                value={this.state.streetAddress}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
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
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
          </div>
          <div className="row">
            <button className="btn btn-warning m-2" onClick={this.updatePerson}>
              Update
            </button>
            <div className={"m-1 alert alert-" + this.state.alertType}>
              {this.state.message}
            </div>
          </div>
        </form>
      </div>
    );
  }

  showMessage = () => {};

  updatePerson = () => {
    const personUpdate = {
      loginData: this.props.session,
      person: this.state,
      personId: this.state.id
    };
    Axios.post(this.props.apiHost + "/person/edit", personUpdate)
      .then((res, req) => {
        this.setState({
          message: "Updated!",
          alertType: "success"
        });
      })
      .catch(err => {
        console.log(err);
        this.setState({
          message: "Can't update. Try again or cntact with admin",
          alertType: "warning"
        });
      });
  };

  getPersonData = () => {
    const requestData = {
      loginData: this.props.session,
      person: {},
      personId: this.props.match.params.personId
    };
    console.log(requestData);
    Axios.post(this.props.apiHost + "/person/profile", requestData)
      .then((res, req) => {
        console.log(res);
        for (let key in res.data) {
          this.setState({ [key]: res.data[key] });
        }
        this.setState({
          message: " ",
          alertType: ""
        });
      })
      .catch(err =>
        this.setState({
          message: "Can't download user data from server",
          alertType: "warning"
        })
      );
  };

  componentWillMount() {
    this.getPersonData();
  }

  goToPage = path => {
    this.props.history.push(path);
  };
}

export default withRouter(PersonEdit);
