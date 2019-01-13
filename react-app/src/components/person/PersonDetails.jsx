import React, { Component } from "react";
import Axios from "axios";
import { withRouter } from "react-router";

class PersonDetails extends Component {
  state = {
    person: {}
  };

  render() {
    return (
      <div className="container">
        <div className="row">
          <button
            className="btn btn-primary m-2"
            onClick={e => this.props.history.push("/person/show")}
          >
            Return
          </button>
        </div>
        <h3>
          {this.state.person.name + " " + this.state.person.surname} details
        </h3>
        <form className="needs-validation">
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">First name</label>
              <input
                disabled
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.person.name}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="lastName">Last name</label>
              <input
                disabled
                id="lastName"
                className="form-control"
                type="text"
                value={this.state.person.surname}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="birthdate">Birthdate</label>
              <input
                disabled
                type="date"
                id="birthdate"
                className="form-control"
                value={this.state.person.birthdate}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="001">Pesel</label>
              <input
                disabled
                type="text"
                id="001"
                className="form-control"
                value={this.state.person.pesel}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="002">Phone number</label>
              <input
                disabled
                type="text"
                id="003"
                className="form-control"
                value={this.state.person.phonenumber}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="003">Role</label>
              <select
                disabled
                type="text"
                id="002"
                className="custom-select d-block w-100"
                value={this.state.person.role}
              >
                <option value="">{this.state.person.role}</option>
              </select>
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="0001">E-mail</label>
              <input
                disabled
                type="email"
                className="form-control"
                id="0001"
                value={this.state.person.email}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0002">Login</label>
              <input
                disabled
                type="text"
                className="form-control"
                id="0002"
                value={this.state.person.login}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0003">Password</label>
              <input
                disabled
                type="password"
                className="form-control"
                id="0003"
                value={this.state.person.password}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-3 mb-3">
              <label htmlFor="city">City</label>
              <input
                disabled
                type="text"
                id="city"
                className="form-control"
                value={this.state.person.city}
              />
            </div>
            <div className="col-md-2 mb-3">
              <label htmlFor="postale">Postale</label>
              <input
                disabled
                type="text"
                id="postale"
                pattern="[1-9]{2}-[0-9]{3}"
                placeholder="__-___"
                className="form-control"
                value={this.state.person.postale}
              />
            </div>
            <div className="col-md-5 mb-3">
              <label htmlFor="street">Street</label>
              <input
                disabled
                type="text"
                id="street"
                className="form-control"
                value={this.state.person.street}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="apartment">Apartment</label>
              <input
                disabled
                type="text"
                pattern="[1-9]*"
                id="apartment"
                className="form-control"
                value={this.state.person.streetAddress}
              />
            </div>
            <div className="col-md-1 mb-3">
              <label htmlFor="suite">Suite</label>
              <input
                disabled
                type="text"
                pattern="[1-9]*[a-z]?"
                id="suite"
                className="form-control"
                value={this.state.person.suite}
              />
            </div>
          </div>
          <button
            className="btn btn-warning"
            onClick={e => this.goToPage("/person/edit/" + this.state.person.id)}
          >
            Edit
          </button>
        </form>
      </div>
    );
  }

  enableEdit = () => {
    this.setState({ edit: "true" });
    console.log(this.state);
  };

  getPersonData() {
    const requestData = {
      loginData: this.props.session,
      personId: this.props.match.params.personId
    };
    console.log(requestData);
    Axios.post(this.props.apiHost + "/person/profile", requestData)
      .then((res, req) => this.setState({ person: res.data }))
      .catch(err => console.log("NIE"));
  }

  componentWillMount() {
    this.getPersonData();
  }

  goToPage = path => {
    this.props.history.push(path);
  };
}

export default withRouter(PersonDetails);
