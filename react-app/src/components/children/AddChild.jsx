import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";

import "./../../css/AddChild.css";

class AddChild extends Component {
  state = {
    id: "",
    name: "",
    surname: "",
    birthdate: "",

    pesel: "",
    admissionDate: "",
    dischargeDate: "",

    comments: "",
    parents: [],
    people: [],
    clazz: "",

    city: "",
    postale: "",
    street: "",
    streetAddress: "",
    suite: "",

    parent1: "",
    parent2: "",

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
              <label htmlFor="002">Admission date</label>
              <input
                type="date"
                id="admissionDate"
                className="form-control"
                value={this.state.admissionDate}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="dischargeDate">Discharge date</label>
              <input
                type="date"
                id="dischargeDate"
                className="form-control"
                value={this.state.role}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
          </div>

          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="comments">Comments</label>
              <textarea
                type="text"
                className="form-control own-text-area"
                id="comments"
                value={this.state.comments}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="row col-md-8 own-column">
              <div className="col-md-6 own-column-no-padding-right">
                <label htmlFor="">Classroom</label>
                <input
                  type="text"
                  className="form-control"
                  id="class"
                  value={this.state.classroom}
                  onChange={e =>
                    this.setState({ [e.target.id]: e.target.value })
                  }
                />
                <label htmlFor="">Form</label>
                <input type="text" className="form-control" />
              </div>
              <div className="col-md-6 own-column-right">
                <label htmlFor="">Parent 1</label>
                <select
                  value={this.state.parent1}
                  type="text"
                  className="custom-select d-block w-100"
                  onChange={e => {
                    this.setState({ parent1: e.target.value });
                    const parentsNew = this.state.people.filter(
                      person =>
                        person.id === parseInt(this.state.parent1) ||
                        person.id === parseInt(this.state.parent2)
                    );
                    this.setState({ parents: parentsNew });
                  }}
                >
                  <option value="">Choose parent from database...</option>
                  {this.state.people.map(person => (
                    <option key={person.id + "p1"} value={person.id}>
                      {person.name + " " + person.surname}
                    </option>
                  ))}
                </select>
                <label htmlFor="">Parent 2</label>
                <select
                  type="text"
                  value={this.state.parent2}
                  className="custom-select d-block w-100"
                  onChange={e => {
                    this.setState({ parent2: e.target.value });
                    const parentsNew = this.state.people.filter(
                      person =>
                        person.id === parseInt(this.state.parent1) ||
                        person.id === parseInt(this.state.parent2)
                    );
                    this.setState({ parents: parentsNew });
                  }}
                >
                  <option value="">Choose parent from database...</option>
                  {this.state.people.map(person => (
                    <option key={person.id + "p2"} value={person.id}>
                      {person.name + " " + person.surname}
                    </option>
                  ))}
                </select>
              </div>
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
      id: "",
      name: "",
      surname: "",
      birthdate: "",

      pesel: "",
      admissionDate: "",
      dischargeDate: "",

      comments: "",
      parents: [],
      class: "",

      city: "",
      postale: "",
      street: "",
      streetAddress: "",
      suite: "",

      formMessage: "",
      alertType: ""
    });
  };

  componentDidMount() {
    Axios.post(this.props.apiHost + "/person/list/parents", this.props.session)
      .then(resp => {
        const people = resp.data;
        this.setState({ people });
      })
      .catch(err => console.log(err));
  }

  submitPerson = () => {
    console.log(this.props.apiHost + "/child/add");
    const requestData = {
      loginData: this.props.session,
      child: this.state,
      childId: null
    };

    Axios.post(this.props.apiHost + "/child/add", requestData)
      .then(
        this.setState({
          alertType: "success",
          formMessage: "Child added!"
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

export default withRouter(AddChild);
