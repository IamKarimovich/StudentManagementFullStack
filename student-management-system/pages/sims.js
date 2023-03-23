import { create } from 'zustand'
import { useState } from 'react'
import MaterialTable, { Column } from '@material-table/core'
import axios from 'axios'
import config from '../config'
import { useEffect } from 'react'

const Sims = () => {
	const [data, setData] = useState([])

	const updateDataset = () => {
		return new Promise((resolve, reject) => {
			axios.get(`${config.baseUrl}/student/getall/`).then((res) => {
				setData(res.data)
				console.log(res.data)
				resolve()
			})
		})
	}

	useEffect(() => {
		updateDataset().then(() => {
			console.log('Dataset fetched')
		})
	}, [])

	return (
		<>
			<MaterialTable
				title="Student Management"
				columns={[
					{
						title: '#',
						field: 'studentNo',
						editable: 'never',
					},
					{
						title: 'Name',
						field: 'name',
						validate: (rowData) => rowData?.name?.length > 0,
					},
					{
						title: 'Surname',
						field: 'surName',
						validate: (rowData) => rowData?.surName?.length > 0,
					},
					{
						title: 'Address',
						field: 'address',
						validate: (rowData) => rowData?.address?.length > 0,
					},
					{
						title: 'Birth Date',
						field: 'birthDate',
						validate: (rowData) => rowData?.birthDate?.length > 0,
					},
					{
						title: 'Login',
						field: 'login',
						validate: (rowData) => rowData?.login?.length >= 3,
					},
					{
						title: 'Password',
						field: 'password',
						validate: (rowData) => rowData?.password?.length >= 3,
					},
				]}
				data={data}
				editable={{
					onRowAdd: (newData) =>
						new Promise((resolve, reject) => {
							var newStudentNo = data.length > 0 ? (
								parseInt(
									data.sort(
										(a, b) =>
											parseInt(a.studentNo) -
											parseInt(b.studentNo)
									)[data.length - 1].studentNo
								) + 1
							).toString() : '1000'
							axios
								.post(`${config.baseUrl}/student/add`, {
									...newData,
									studentNo: newStudentNo,
								})
								.then((data) => {
									console.log(data)
									updateDataset().then(() => {
										resolve()
									})
								})
						}),
					onRowUpdate: (newData, oldData) =>
						new Promise((resolve, reject) => {
							axios
								.put(
									`${config.baseUrl}/student/updatestudent/${oldData.studentNo}`,
									{
										...oldData,
										...newData,
									}
								)
								.then((data) => {
									console.log(data)
									updateDataset().then(() => {
										resolve()
									})
								})
						}),
					onRowDelete: (oldData) =>
						new Promise((resolve, reject) => {
							axios
								.delete(
									`${config.baseUrl}/student/withNo/${oldData.studentNo}`
								)
								.then((data) => {
									console.log(data)
									updateDataset().then(() => {
										resolve()
									})
								})
						}),
				}}
			/>
		</>
	)
}

export default Sims
