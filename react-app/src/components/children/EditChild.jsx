import React, { Component } from "react";
import { withRouter } from "react-router";
import Axios from "axios";
import Select from "react-select";

class EditChild extends Component {
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
    clazzes: [],

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
            onClick={e => this.props.history.push("/children/show")}
          >
            Return
          </button>
        </div>
        <h3>{this.state.name + " " + this.state.surname} details</h3>
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
              <label htmlFor="surname">Last name</label>
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
                id="001"
                className="form-control"
                value={this.state.pesel}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="003">Admission date</label>
              <input
                type="date"
                id="003"
                className="form-control"
                value={this.state.admissionDate}
                onChange={e => this.setState({ [e.target.id]: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="002">Discharge date</label>
              <input
                type="date"
                id="002"
                className="form-control"
                value={this.state.dischargeDate}
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
            {this.state.people.map(parent => (
              <div className="col-md-6" key={parent.id}>
                <label htmlFor="parent">Parent</label>
                <div className="input-group">
                  <input type="text" id="parent" className="form-control" />
                  <span className="input-group-btn btn-right">
                    <button
                      className="btn btn-light"
                      type="button"
                      onClick={() => this.props.history.push("/children/show")}
                    >
                      PARENT DETAILS
                    </button>
                  </span>
                </div>
              </div>
            ))}
          </div>
          <div className="row" />
          <button
            className="btn btn-warning m-2"
            onClick={e => {
              this.sendEdit();
            }}
          >
            Edit
          </button>
        </form>
      </div>
    );
  }

  sendEdit = () => {
    let requestData = {
      loginData: this.props.session,
      child: this.state,
      childId: this.props.match.params.childId
    };

    console.log(this.props.apiHost + "/child/edit", requestData);
    Axios.post(this.props.apiHost + "/child/edit", requestData)
      .then((res, req) => {
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      });
  };

  componentDidMount = () => {
    let requestData = {
      loginData: this.props.session,
      childId: this.props.match.params.childId
    };

    Axios.post(this.props.apiHost + "/child/profile", requestData)
      .then((res, req) =>
        this.setState({
          id: res.data.id,
          name: res.data.name,
          surname: res.data.surname,
          birthdate: res.data.birthdate,

          pesel: res.data.pesel,
          admissionDate: res.data.admissionDate,
          dischargeDate: res.data.dischargeDate,

          comments: res.data.comments,
          parents: res.data.parents,
          people: res.data.people,
          clazz: res.data.clazz,
          clazzes: res.data.clazzes,

          city: res.data.city,
          postale: res.data.postale,
          street: res.data.street,
          streetAddress: res.data.streetAddress,
          suite: res.data.suite,

          parent1: res.data.parent1,
          parent2: res.data.parent
        })
      )
      .catch(err => console.log(err));
  };
}

export default withRouter(EditChild);
